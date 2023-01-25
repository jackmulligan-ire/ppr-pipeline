import { getByText, render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import App from "./App";

describe("<App />", () => {
  it("Changes the statistic being shown via the metric input", () => {
    const { container } = render(<App />);
    const graphContainer = container.getElementsByClassName(
      "highcharts-container"
    )[0];

    expect(
      getByText(graphContainer as HTMLElement, /median sale price/i)
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
