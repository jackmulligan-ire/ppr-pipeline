import Button from "@mui/material/Button";
import ButtonGroup from "@mui/material/ButtonGroup";
import { Dispatch, SetStateAction } from "react";

interface ChartTimeControlsProps {
  setYearsVisible: Dispatch<SetStateAction<number>>;
}

const fieldMap = {
  "1Y": 1,
  "3Y": 3,
  "5Y": 5,
  ALL: new Date().getFullYear() - 2010,
};

function ChartTimeControls({ setYearsVisible }: ChartTimeControlsProps) {
  return (
    <ButtonGroup variant="outlined" size="small">
      {Object.entries(fieldMap).map(([key, value]) => (
        <Button
          key={`button-${key.toLowerCase()}`}
          onClick={() => setYearsVisible(value)}
        >
          {key}
        </Button>
      ))}
    </ButtonGroup>
  );
}

export default ChartTimeControls;
