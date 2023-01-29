import { useContext } from "react";
import HighchartsReact from "highcharts-react-official";
import * as Highcharts from "highcharts";
import SelectedLocationsContext from "../../../contexts/SelectedLocationsContext";
import DataContext from "../../../contexts/DataContext";
import {
  PropertyTransactions,
  SequentialData,
} from "../../../contexts/DataContext/DataContext";
import ActiveMetricContext from "../../../contexts/ActiveMetricContext";

interface ChartDisplayProps {
  yearsVisible: number;
}

Highcharts.setOptions({
  lang: {
    thousandsSep: ",",
  },
});

function ChartDisplay({ yearsVisible }: ChartDisplayProps) {
  const selectedLocations = useContext(SelectedLocationsContext);
  const data = useContext(DataContext);
  const activeMetric = useContext(ActiveMetricContext);
  const yearInMs = 365 * 24 * 3600 * 1000;

  const getSeries = (
    inputData: PropertyTransactions[],
    activeMetric: { label: string; value: string },
    yearsVisible: number
  ): Highcharts.SeriesOptionsType[] => {
    const getProcessedData = (rawData: SequentialData[]) => {
      return rawData
        .map((data) => [
          Date.UTC(data.year, data.month - 1),
          data[activeMetric.value as keyof SequentialData],
        ])
        .filter(([date]) => Date.now() - date < yearsVisible * yearInMs);
    };

    return inputData
      .filter((child) => selectedLocations.includes(child.location))
      .map((child) => {
        return {
          type: "line",
          data: getProcessedData(child.data),
          name: child.location,
        };
      });
  };

  const options: Highcharts.Options = {
    series: getSeries(data, activeMetric, yearsVisible),
    title: {
      text: undefined,
    },
    xAxis: {
      type: "datetime",
      title: {
        text: "Year",
      },
      tickInterval: yearInMs,
    },
    yAxis: {
      title: {
        text: activeMetric.label,
      },
    },
    tooltip: {
      pointFormat:
        "<span style='color:{series.color}'>\u25CF</span> <b>{series.name}</b>: {point.y}<br />",
      xDateFormat: "%b %Y",
      shared: true,
    },
  };

  return (
    <>
      <HighchartsReact highcharts={Highcharts} options={options} />
    </>
  );
}

export default ChartDisplay;
