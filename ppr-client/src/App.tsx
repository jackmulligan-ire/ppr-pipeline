import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";

import MetricInput from "./components/MetricInput";
import LocationInput from "./components/LocationInput";
import LocationDataGrid from "./components/LocationDataGrid";
import SelectedLocationProvider from "./providers/SelectedLocationsProvider";
import ChartTimeControls from "./components/ChartTimeControls";

function App() {
  return (
    <SelectedLocationProvider>
      <Container maxWidth="xl">
        <Grid container flexDirection="column" mx="auto">
          <Grid item mb={3}>
            <MetricInput />
          </Grid>
          <Grid item mb={3}>
            <ChartTimeControls />
          </Grid>
          <Grid item mb={3}>
            <LocationDataGrid />
            <LocationInput />
          </Grid>
        </Grid>
      </Container>
    </SelectedLocationProvider>
  );
}

export default App;
