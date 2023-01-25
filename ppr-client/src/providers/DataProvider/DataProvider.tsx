import { ReactNode, useEffect, useState } from "react";
import DataContext from "../../contexts/DataContext";
import { PropertyTransactions } from "../../contexts/DataContext/DataContext";

function DataProvider(props: { children: ReactNode }) {
  const [data, setData] = useState<PropertyTransactions[]>([]);

  useEffect(() => {
    fetch("http://localhost/api/property-transaction-stats")
      .then((response) => response.json())
      .then((data) => setData(data))
      .catch((error) => console.log(`Error: ${error}`));
  }, []);

  return (
    <DataContext.Provider value={data}>{props.children}</DataContext.Provider>
  );
}

export default DataProvider;
