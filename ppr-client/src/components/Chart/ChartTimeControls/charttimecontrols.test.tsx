import { render, screen } from "@testing-library/react";
import ChartTimeControls from "./ChartTimeControls";

describe("<ChartTimeControls />", () => {
  it("Renders 4 buttons with each field", () => {
    render(<ChartTimeControls setYearsVisible={jest.fn()} />);
    ["1Y", "3Y", "5Y", "ALL"].forEach((field) => {
      expect(
        screen.getByRole("button", {
          name: new RegExp(field, "i"),
        })
      ).toBeInTheDocument();
    });
  });
});
