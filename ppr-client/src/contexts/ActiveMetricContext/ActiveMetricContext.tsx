import React from "react";

export type ActiveMetric = {
  label: string;
  value: string;
};

const ActiveMetricContext = React.createContext<ActiveMetric>({
  label: "",
  value: "",
});

export default ActiveMetricContext;
