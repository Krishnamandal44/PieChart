package com.example.krishna.piechart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private float rainFall[]={98.8f, 123.8f, 161.6f, 24.2f, 52f, 58.2f, 35.4f, 13.8f, 78.4f, 203.4f, 240.2f, 159.7f};
    private String monthsNames[]={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec",};
    private PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpPieChart();
    }

    private void setUpPieChart() {
        //populating a list of pie Entries
        List<PieEntry> pieEntries=new ArrayList<>();
        for(int i=0;i<rainFall.length;i++){
            pieEntries.add(new PieEntry(rainFall[i],monthsNames[i]));
        }

        PieDataSet dataSet=new PieDataSet(pieEntries,"Rainfall for Vancouver");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(dataSet);

        // Get the Chart
        pieChart =(PieChart)findViewById(R.id.chart);

        pieChart.setRotationEnabled(false);
//        pieChart.setUserPercentValues(true);
//        pieChart.setHoleColor(Color.BLUE);
//        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Super Cool Chart");
        pieChart.setCenterTextSize(10);
//        pieChart.setDrawEntryLabels(true);
//        pieChart.setEntryLabelTextSize(20); ////for text size


//        pieChart.setData(data);
//        pieChart.animateY(1000);
//        pieChart.invalidate();
        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                int pos1=e.toString().indexOf("(sum): ");
                String rainfall=e.toString().substring(pos1+7);

                for(int i=0;i<rainFall.length;i++){
                    if(rainFall[i]==Float.parseFloat(rainfall)){
                        pos1=i;
                        break;
                    }
                }

                String months=monthsNames[pos1+1];
                Toast.makeText(MainActivity.this, "Months"+months+"\n"+"Rainfall"+rainfall, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void addDataSet() {

        ArrayList<PieEntry> yEntries=new ArrayList<>();
        ArrayList<String> xEntries=new ArrayList<>();
        for(int i=0;i<rainFall.length;i++){
            yEntries.add(new PieEntry(rainFall[i],i));
        }

        for(int i=0;i<monthsNames.length;i++){
            xEntries.add(monthsNames[i]);
        }

         /////////create the data set

        PieDataSet pieDataSet=new PieDataSet(yEntries,"Employee sales");

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        /////// add colors to data set
        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        colors.add(Color.GREEN);
        colors.add(Color.DKGRAY);
        colors.add(Color.BLACK);
        colors.add(Color.LTGRAY);
        colors.add(Color.GREEN);
        colors.add(Color.RED);

        pieDataSet.setColors(colors);

        ////add legend to chart

        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        /////create pieData Object

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}
