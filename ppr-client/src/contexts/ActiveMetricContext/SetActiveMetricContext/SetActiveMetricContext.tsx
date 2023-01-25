import React, { Dispatch, SetStateAction } from "react";
import { ActiveMetric } from "../ActiveMetricContext";

const SetActiveMetricContext = React.createContext<
  Dispatch<SetStateAction<ActiveMetric>> | undefined
>(undefined);

export default SetActiveMetricContext;
