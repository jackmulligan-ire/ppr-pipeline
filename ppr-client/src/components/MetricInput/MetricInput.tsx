import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import MenuItem from "@mui/material/MenuItem";
import useSetActiveMetric from "../../hooks/useSetActiveMetric";

const statistics = [
  {
    value: "meanPrice",
    label: "Mean Price",
  },
  {
    value: "medianPrice",
    label: "Median Price",
  },
  {
    value: "transactions",
    label: "Transactions",
  },
];

function MetricInput() {
  const setActiveMetric = useSetActiveMetric();

  return (
    <Grid container>
      <Grid item xs={12} sm={6}>
        <TextField
          select
          fullWidth
          defaultValue="medianPrice"
          label="Select Metric"
        >
          {statistics.map((option) => (
            <MenuItem
              key={option.value}
              value={option.value}
              onClick={() =>
                setActiveMetric({ value: option.value, label: option.label })
              }
            >
              {option.label}
            </MenuItem>
          ))}
        </TextField>
      </Grid>
    </Grid>
  );
}

export default MetricInput;
