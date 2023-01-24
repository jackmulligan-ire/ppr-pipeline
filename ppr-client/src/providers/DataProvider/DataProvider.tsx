import { ReactNode } from "react";
import DataContext from "../../contexts/DataContext";
import { dataSample } from "../../utils/utils";

function DataProvider(props: { children: ReactNode }) {
  return (
    <DataContext.Provider value={dataSample}>
      {props.children}
    </DataContext.Provider>
  );
}

export default DataProvider;
