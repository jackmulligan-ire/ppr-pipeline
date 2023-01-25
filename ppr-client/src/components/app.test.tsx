import { findByText, getByText, render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import App from "./App";
import { dataSample } from "../utils/utils";

describe("<App />", () => {
  beforeEach(() => {
    fetchMock.resetMocks();
  });

  it("Changes the statistic being shown via the metric input", async () => {
    fetchMock.mockResponseOnce(JSON.stringify(dataSample));

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
});
