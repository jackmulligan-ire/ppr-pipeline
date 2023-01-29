import { findByText, getByText, render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import App from "./App";
import { dataSample } from "../utils/utils";

describe("<App />", () => {
  beforeEach(() => {
    fetchMock.resetMocks();
    fetchMock.mockResponseOnce(JSON.stringify(dataSample));
  });

  it("Changes the statistic being shown via the metric input", async () => {
    const { container } = render(<App />);
    const graphContainer = container.getElementsByClassName(
      "highcharts-container"
    )[0];

    expect(
      await findByText(graphContainer as HTMLElement, /median price/i)
    ).toBeInTheDocument();
    userEvent.click(screen.getByLabelText(/select metric/i));
    userEvent.click(
      screen.getByRole("option", {
        name: /transactions/i,
      })
    );
    expect(
      getByText(graphContainer as HTMLElement, /transactions/i)
    ).toBeInTheDocument();
  });

  it("Dublin is selected in the locations menu and displayed in the graph on first render", async () => {
    const { container } = render(<App />);
    const graphContainer = container.getElementsByClassName(
      "highcharts-container"
    )[0];

    expect(screen.getByRole("button", { name: /dublin/i })).toBeInTheDocument();
    expect(
      await findByText(graphContainer as HTMLElement, /dublin/i)
    ).toBeInTheDocument();
  });

  it("New option is selected from the autocomplete, this is reflected in the chart", async () => {
    const { container } = render(<App />);
    const graphContainer = container.getElementsByClassName(
      "highcharts-container"
    )[0];

    userEvent.click(screen.getByLabelText(/locations/i));
    userEvent.click(await screen.findByText(/carlow/i));

    expect(
      await findByText(graphContainer as HTMLElement, /carlow/i)
    ).toBeInTheDocument();
  });
});
