import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import SelectedLocationsProvider from "../../providers/SelectedLocationsProvider";
import LocationDataGrid from "./LocationDataGrid";

describe("<LocationDataGrid />", () => {
  it("Renders all column headers", () => {
    render(<LocationDataGrid />);
    expect(screen.getByText(/location/i)).toBeInTheDocument();
    expect(screen.getByText(/data/i)).toBeInTheDocument();
  });

  it("Renders first location and its data", () => {
    render(<LocationDataGrid />, {
      wrapper: SelectedLocationsProvider,
    });
    expect(screen.getByText(/Carlow/i)).toBeInTheDocument();
    expect(screen.getByText(/300k/i)).toBeInTheDocument();
  });
});
