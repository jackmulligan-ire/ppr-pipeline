import React from "react";

const ActiveMetricContext = React.createContext<{
  label: string;
  value: string;
}>({ label: "", value: "" });

export default ActiveMetricContext;
