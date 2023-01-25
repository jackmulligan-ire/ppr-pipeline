import { ReactNode, useState } from "react";
import ActiveMetricContext, {
  ActiveMetric,
} from "../../contexts/ActiveMetricContext/ActiveMetricContext";
import SetActiveMetricContext from "../../contexts/ActiveMetricContext/SetActiveMetricContext";

function ActiveMetricProvider(props: { children: ReactNode }) {
  const [activeMetric, setActiveMetric] = useState<ActiveMetric>({
    label: "Median Sale Price",
    value: "medianSalePrice",
  });

  return (
    <SetActiveMetricContext.Provider value={setActiveMetric}>
      <ActiveMetricContext.Provider value={activeMetric}>
        {props.children}
      </ActiveMetricContext.Provider>
    </SetActiveMetricContext.Provider>
  );
}

export default ActiveMetricProvider;
