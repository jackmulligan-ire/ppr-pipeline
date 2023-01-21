import { ReactNode } from "react";
import SelectedLocationsContext from "../../contexts/SelectedLocationsContext";

function SelectedLocationsProvider(props: { children: ReactNode }) {
  return (
    <SelectedLocationsContext.Provider value={["Carlow"]}>
      {props.children}
    </SelectedLocationsContext.Provider>
  );
}

export default SelectedLocationsProvider;
