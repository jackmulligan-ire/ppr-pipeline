import { render, screen } from "@testing-library/react";
import Chart from "./Chart";
import SelectedLocationsContext from "../../contexts/SelectedLocationsContext";
import DataContext from "../../contexts/DataContext";
import ActiveMetric from "../../contexts/ActiveMetricContext";
import { dataSample } from "../../utils/utils";
import userEvent from "@testing-library/user-event";

const setup = () =>
  render(
    <ActiveMetric.Provider
      value={{ value: "medianSalePrice", label: "Median Sale Price" }}
    >
      <DataContext.Provider value={dataSample}>
        <SelectedLocationsContext.Provider value={["Carlow", "Cavan"]}>
          <Chart />
        </SelectedLocationsContext.Provider>
      </DataContext.Provider>
    </ActiveMetric.Provider>
  );

describe("<Chart />", () => {
  it("Renders the last 3 years of data by default", () => {
    setup();

    const currentYear = new Date().getFullYear();
    expect(
      screen.getByText(new RegExp(currentYear.toString(), "i"))
    ).toBeInTheDocument();
    expect(
      screen.getByText(new RegExp((currentYear - 1).toString(), "i"))
    ).toBeInTheDocument();
    expect(
      screen.getByText(new RegExp((currentYear - 2).toString(), "i"))
    ).toBeInTheDocument();
    expect(
      screen.queryByText(new RegExp((currentYear - 3).toString(), "i"))
    ).not.toBeInTheDocument();
  });

  it("Renders the last 5 years of data when that button is clicked", () => {
    setup();
    const currentYear = new Date().getFullYear();

    expect(
      screen.queryByText(new RegExp((currentYear - 3).toString(), "i"))
    ).not.toBeInTheDocument();

    const fiveYearButton = screen.getByRole("button", { name: /5y/i });
    userEvent.click(fiveYearButton);

    expect(
      screen.getByText(new RegExp(currentYear.toString(), "i"))
    ).toBeInTheDocument();
    expect(
      screen.getByText(new RegExp((currentYear - 3).toString(), "i"))
    ).toBeInTheDocument();
    expect(
      screen.queryByText(new RegExp((currentYear - 4).toString(), "i"))
    ).not.toBeInTheDocument();
  });
});
