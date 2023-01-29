import LocationInput from "./LocationInput";
import { render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import DataProvider from "../../providers/DataProvider";
import { dataSample } from "../../utils/utils";
import SelectedLocationsProvider from "../../providers/SelectedLocationsProvider";

const setup = () =>
  render(
    <DataProvider>
      <SelectedLocationsProvider>
        <LocationInput />
      </SelectedLocationsProvider>
    </DataProvider>
  );

describe("<LocationInput />", () => {
  beforeEach(() => {
    fetchMock.resetMocks();
    fetchMock.mockResponseOnce(JSON.stringify(dataSample));
  });

  it("Renders an Autocomplete with label of 'Locations'", () => {
    setup();
    expect(screen.getByLabelText(/locations/i)).toBeInTheDocument();
  });

  it("Should have helper text of 'Add a county'", () => {
    setup();
    expect(screen.getByText(/Add a county/i)).toBeInTheDocument();
  });

  it("Should show the locations returned by the endpoint in the dropdown", async () => {
    setup();
    const inputElem = screen.getByLabelText(/locations/i);

    userEvent.click(inputElem);
    expect(await screen.findByText(/Carlow/)).toBeInTheDocument();
    expect(await screen.findByText(/Cavan/)).toBeInTheDocument();
    // Loaded in autocomplete by default
    expect(await screen.findAllByText(/Dublin/)).toHaveLength(2);
  });

  it("Shows Dublin as selected options on the initial render", () => {
    setup();
    expect(
      screen.getByRole("button", {
        name: /dublin/i,
      })
    ).toBeInTheDocument();
  });

  it.todo("Limits the selected option to 5 tags");
});
