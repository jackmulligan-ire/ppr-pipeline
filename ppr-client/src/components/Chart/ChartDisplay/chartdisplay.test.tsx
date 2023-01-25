import { render, screen } from "@testing-library/react";
import SelectedLocationsContext from "../../../contexts/SelectedLocationsContext";
import DataContext from "../../../contexts/DataContext";
import ChartDisplay from "./ChartDisplay";
import { dataSample } from "../../../utils/utils";
import ActiveMetric from "../../../contexts/ActiveMetricContext";

const setup = () =>
  render(
    <ActiveMetric.Provider
      value={{ value: "medianSalePrice", label: "Median Sale Price" }}
    >
      <DataContext.Provider value={dataSample}>
        <SelectedLocationsContext.Provider value={["Carlow", "Cavan"]}>
          <ChartDisplay yearsVisible={3} />
        </SelectedLocationsContext.Provider>
      </DataContext.Provider>
    </ActiveMetric.Provider>
  );

describe("<ChartDisplay />", () => {
  it("Shows the locations and data for the locations selected", () => {
    setup();
    expect(screen.getByText(/Carlow/i)).toBeInTheDocument();
    expect(screen.getByText(/Cavan/i)).toBeInTheDocument();
    expect(screen.queryByText(/Dublin/i)).not.toBeInTheDocument();
    expect(screen.getByText(/300k/i)).toBeInTheDocument();
    expect(screen.getByText(/400k/i)).toBeInTheDocument();
    expect(screen.getByText(/500k/i)).toBeInTheDocument();
  });

  it("Shows the active statistic as the y-label of the graph", () => {
    setup();
    expect(screen.getByText(/Median Sale Price/i)).toBeInTheDocument();
  });

  it("Shows only the values on the y-axis for the active statistic", () => {
    setup();
    expect(screen.queryByText(/\b20\b/)).not.toBeInTheDocument();
    expect(screen.queryByText(/\b50\b/)).not.toBeInTheDocument();
  });

  it("Only renders the relative datapoints for the time horizon", () => {
    render(
      <ActiveMetric.Provider
        value={{ value: "medianSalePrice", label: "Median Sale Price" }}
      >
        <DataContext.Provider value={dataSample}>
          <SelectedLocationsContext.Provider value={["Carlow", "Cavan"]}>
            <ChartDisplay yearsVisible={1} />
          </SelectedLocationsContext.Provider>
        </DataContext.Provider>
      </ActiveMetric.Provider>
    );
    const currentYear = new Date().getFullYear();
    expect(
      screen.getByText(new RegExp(currentYear.toString(), "i"))
    ).toBeInTheDocument();
    expect(
      screen.queryByText(new RegExp((currentYear - 1).toString(), "i"))
    ).not.toBeInTheDocument();
  });
});
