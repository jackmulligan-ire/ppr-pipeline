import LocationInput from "./LocationInput";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";

const setup = () => render(<LocationInput />);

describe("<LocationInput />", () => {
  it("Renders an Autocomplete with label of 'Locations'", () => {
    setup();
    expect(screen.getByLabelText(/locations/i)).toBeInTheDocument();
  });

  it("Should have helper text of 'Add a county'", () => {
    setup();
    expect(screen.getByText(/Add a county/i)).toBeInTheDocument();
  });

  it("Should show at least 3 options when the user clicks the input", () => {
    setup();
    const inputElem = screen.getByLabelText(/locations/i);
    userEvent.click(inputElem);
    expect(screen.getByText(/Carlow/)).toBeInTheDocument();
    expect(screen.getByText(/Cavan/)).toBeInTheDocument();
    expect(screen.getByText(/Clare/)).toBeInTheDocument();
  });
});
