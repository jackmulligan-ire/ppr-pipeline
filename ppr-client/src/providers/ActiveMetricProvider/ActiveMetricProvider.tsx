import { ReactNode } from "react";
import ActiveMetricContext from "../../contexts/ActiveMetricContext/ActiveMetricContext";

function ActiveMetricProvider(props: { children: ReactNode }) {
  return (
    <ActiveMetricContext.Provider
      value={{ label: "Median Sale Price", value: "medianSalePrice" }}
    >
      {props.children}
    </ActiveMetricContext.Provider>
  );
}

export default ActiveMetricProvider;
