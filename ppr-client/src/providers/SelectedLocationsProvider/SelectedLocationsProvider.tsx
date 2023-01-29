import { ReactNode, useState } from "react";
import SelectedLocationsContext from "../../contexts/SelectedLocationsContext";
import SetSelectedLocationsContext from "../../contexts/SelectedLocationsContext/SetSelectedLocationsContext";

function SelectedLocationsProvider(props: { children: ReactNode }) {
  const [selectedLocations, setSelectedLocations] = useState<string[]>([
    "Dublin",
  ]);

  return (
    <SetSelectedLocationsContext.Provider value={setSelectedLocations}>
      <SelectedLocationsContext.Provider value={selectedLocations}>
        {props.children}
      </SelectedLocationsContext.Provider>
    </SetSelectedLocationsContext.Provider>
  );
}

export default SelectedLocationsProvider;
