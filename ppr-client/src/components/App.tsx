import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";

import MetricInput from "./MetricInput";
import LocationInput from "./LocationInput";
import LocationDataGrid from "./LocationDataGrid";
import SelectedLocationProvider from "../providers/SelectedLocationsProvider";
import DataProvider from "../providers/DataProvider";
import Chart from "./Chart";
import ActiveMetricProvider from "../providers/ActiveMetricProvider";

function App() {
  return (
    <ActiveMetricProvider>
      <DataProvider>
        <SelectedLocationProvider>
          <Container maxWidth="xl">
            <Grid container flexDirection="column" mx="auto">
              <Grid item mb={3} xs={12}>
                <MetricInput />
              </Grid>
              <Grid item mb={3} xs={12}>
                <Chart />
              </Grid>
              <Grid item mb={3} xs={12}>
                <LocationDataGrid />
                <LocationInput />
              </Grid>
            </Grid>
          </Container>
        </SelectedLocationProvider>
      </DataProvider>
    </ActiveMetricProvider>
  );
}

export default App;
