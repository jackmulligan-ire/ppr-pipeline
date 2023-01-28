import Autocomplete from "@mui/material/Autocomplete";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import { useContext } from "react";
import DataContext from "../../contexts/DataContext";
import SelectedLocationsContext from "../../contexts/SelectedLocationsContext";
import useSetSelectedLocations from "../../hooks/useSetSelectedLocations";

function LocationInput() {
  const data = useContext(DataContext);
  const selectedLocations = useContext(SelectedLocationsContext);
  const setSelectedLocations = useSetSelectedLocations();
  const locationOptions = data.map((item) => item.location);

  const handleChange = (e: React.SyntheticEvent, val: string[]) =>
    setSelectedLocations(val);

  return (
    <Grid container>
      <Grid item xs={12} sm={6}>
        <Autocomplete
          multiple
          onChange={handleChange}
          disablePortal
          value={selectedLocations}
          options={locationOptions}
          getOptionDisabled={(option) =>
            selectedLocations.length >= 5 || selectedLocations.includes(option)
          }
          renderInput={(params) => (
            <TextField
              {...params}
              label="Locations"
              helperText="Add a county (max 5)"
            />
          )}
        />
      </Grid>
    </Grid>
  );
}

export default LocationInput;
