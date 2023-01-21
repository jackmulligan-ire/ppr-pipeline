import Button from "@mui/material/Button";
import ButtonGroup from "@mui/material/ButtonGroup";

function ChartTimeControls() {
  const fields = ["1Y", "3Y", "5Y", "ALL"];

  return (
    <ButtonGroup variant="outlined" size="small">
      {fields.map((field) => (
        <Button key={`button-${field.toLowerCase()}`}>{field}</Button>
      ))}
    </ButtonGroup>
  );
}

export default ChartTimeControls;
