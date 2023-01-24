import { render, screen } from "@testing-library/react";
import LocationDataGrid from "./LocationDataGrid";
import SelectedLocationsContext from "../../contexts/SelectedLocationsContext";

describe("<LocationDataGrid />", () => {
  it("Renders all column headers", () => {
    render(<LocationDataGrid />);
    expect(screen.getByText(/location/i)).toBeInTheDocument();
    expect(screen.getByText(/data/i)).toBeInTheDocument();
  });

  it("Renders first location and its data", () => {
    render(
      <SelectedLocationsContext.Provider value={["Carlow"]}>
        <LocationDataGrid />
      </SelectedLocationsContext.Provider>
    );
    expect(screen.getByText(/Carlow/i)).toBeInTheDocument();
    expect(screen.getByText(/300k/i)).toBeInTheDocument();
  });
});
