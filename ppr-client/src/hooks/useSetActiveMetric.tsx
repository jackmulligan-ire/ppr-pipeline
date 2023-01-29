import { useContext } from "react";
import SetActiveMetricContext from "../contexts/ActiveMetricContext/SetActiveMetricContext";

function useSetActiveMetric() {
  const setActiveMetric = useContext(SetActiveMetricContext);

  if (!setActiveMetric)
    throw new Error("<SetActiveMetricContext.Provider is undefined");

  return setActiveMetric;
}

export default useSetActiveMetric;
