import Container from "@mui/material/Container";
import MetricInput from "./components/MetricInput";
import LocationInput from "./components/LocationInput";

function App() {
  return (
    <Container maxWidth="xl">
      <MetricInput />
      <LocationInput />
    </Container>
  );
}

export default App;
