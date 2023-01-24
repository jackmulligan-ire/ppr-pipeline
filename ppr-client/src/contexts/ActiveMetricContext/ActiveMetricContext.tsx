import React from "react";

const ActiveMetricContext = React.createContext<
  { label: string; value: string } | undefined
>(undefined);

export default ActiveMetricContext;
