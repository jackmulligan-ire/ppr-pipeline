import Autocomplete from "@mui/material/Autocomplete";
import TextField from "@mui/material/TextField";

const locationOptions = ["Carlow", "Cavan", "Clare"];

function LocationInput() {
  return (
    <Autocomplete
      multiple
      limitTags={5}
      disablePortal
      options={locationOptions}
      renderInput={(params) => (
        <TextField {...params} label="Locations" helperText="Add a county" />
      )}
    />
  );
}

export default LocationInput;
