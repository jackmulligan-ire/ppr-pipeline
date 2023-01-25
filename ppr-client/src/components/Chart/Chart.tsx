import { useState } from "react";
import ChartDisplay from "./ChartDisplay";
import ChartTimeControls from "./ChartTimeControls";

function Chart() {
  const [yearsVisible, setYearsVisible] = useState<number>(3);

  return (
    <>
      <ChartTimeControls setYearsVisible={setYearsVisible} />
      <ChartDisplay yearsVisible={yearsVisible} />
    </>
  );
}

export default Chart;
