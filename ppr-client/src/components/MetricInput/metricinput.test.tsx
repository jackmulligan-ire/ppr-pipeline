import { getByText, render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import ActiveMetricProvider from "../../providers/ActiveMetricProvider";
import MetricInput from "./MetricInput";

const setup = () =>
  render(
    <ActiveMetricProvider>
      <MetricInput />
    </ActiveMetricProvider>
  );

describe("<MetricInput />", () => {
  it("Shows an input with 'Select Metric'", () => {
    setup();
    expect(screen.getByLabelText(/select metric/i)).toBeInTheDocument();
  });

  it("Shows a default option of 'Median Sale Price'", () => {
    setup();
    const inputElem = screen.getByLabelText(/select metric/i);
    expect(getByText(inputElem, /Median Sale Price/i)).toBeInTheDocument();
  });

  it("Shows all 3 options available on screen", () => {
    setup();
    const inputElem = screen.getByLabelText(/select metric/i);
    userEvent.click(inputElem);
    expect(screen.queryAllByText(/Median Sale Price/i)).not.toBe([]);
    expect(screen.getByText(/Mean Sale Price/i)).toBeInTheDocument();
    expect(screen.getByText(/Transactions/i)).toBeInTheDocument();
  });
});
