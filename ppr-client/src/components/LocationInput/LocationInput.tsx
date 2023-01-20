import Autocomplete from "@mui/material/Autocomplete";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";

const locationOptions = ["Carlow", "Cavan", "Clare"];

function LocationInput() {
  return (
    <Grid container>
      <Grid item xs={12} sm={6}>
        <Autocomplete
          multiple
          limitTags={5}
          disablePortal
          options={locationOptions}
          renderInput={(params) => (
            <TextField
              {...params}
              label="Locations"
              helperText="Add a county"
            />
          )}
        />
      </Grid>
    </Grid>
  );
}

export default LocationInput;
