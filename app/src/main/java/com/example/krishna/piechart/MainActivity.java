package com.example.krishna.piechart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private float rainFall[]={98.8f, 123.8f, 161.6f, 24.2f, 52f, 58.2f, 35.4f};
    private String monthsNames[]={"jan","feb","mar","apr","may","jun","jul"};
    private PieChart pieChart;
    private RelativeLayout mainLayout;
    public static String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout=(RelativeLayout) findViewById(R.id.mainLayout);
        pieChart=new PieChart(this);
        mainLayout.setBackgroundColor(Color.LTGRAY);
//        mainLayout.addView(pieChart);
//        pieChart.setUsePercentValues(true);
//        pieChart.setDrawEntryLabels(true);
//        pieChart.setHoleRadius(7);
//        pieChart.setTransparentCircleRadius(10);
//        pieChart.setRotationAngle(0);
//        pieChart.setRotationEnabled(true);
//        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//                if(e==null)
//                    return;;
//                //Toast.makeText(MainActivity.this, monthsNames[e.getX()"", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });
//        addData();
//        Legend legend=pieChart.getLegend();
//        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//        legend.setXEntrySpace(7);
//        legend.setYEntrySpace(5);





        setUpPieChart();
    }

    private void addData() {

        ArrayList<PieEntry> yEntries=new ArrayList<>();
        ArrayList<String> xEntries=new ArrayList<>();
        for(int i=0;i<rainFall.length;i++){
            yEntries.add(new PieEntry(rainFall[i],i));
        }

        for(int i=0;i<monthsNames.length;i++){
            xEntries.add(monthsNames[i]);
        }

        /////////create the data set

        PieDataSet pieDataSet=new PieDataSet(yEntries,"");

        pieDataSet.setSliceSpace(3);
        pieDataSet.setValueTextSize(5);

        /////// add colors to data set
        ArrayList<Integer> colors=new ArrayList<>();

        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);


        for(int c: ColorTemplate.JOYFUL_COLORS)
            colors.add(c);


        for(int c: ColorTemplate.COLORFUL_COLORS)
            colors.add(c);


        for(int c: ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colors);
        PieData pieData=new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.GRAY);
        pieChart.setData(pieData);
        pieChart.highlightValue(null);
        pieChart.invalidate();




    }

    private void setUpPieChart() {
        //populating a list of pie Entries
//        List<PieEntry> pieEntries=new ArrayList<>();
//        for(int i=0;i<rainFall.length;i++){
//            pieEntries.add(new PieEntry(rainFall[i],monthsNames[i]));
//        }
//
//        PieDataSet dataSet=new PieDataSet(pieEntries,"Rainfall for Vancouver");
//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        PieData data=new PieData(dataSet);

        // Get the Chart
        pieChart =(PieChart)findViewById(R.id.chart);
        pieChart.setDescription(null);


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

                Log.d(TAG,"On Value Selected: value selected from chart");
                Log.d(TAG,"On Value Selected: "+e.toString());
                Log.d(TAG,"On Value Selected: "+h.toString());

                int pos1=e.toString().indexOf("y: ");
                String rainfall=e.toString().substring(pos1+3);

                for(int i=0;i<rainFall.length;i++){
                    if(rainFall[i]==Float.parseFloat(rainfall)){
                        pos1=i;
                        break;
                    }
                }

                String months=monthsNames[pos1];
                Toast.makeText(MainActivity.this, "Months:   "+months+"\n"+"Rainfall:  "+rainfall, Toast.LENGTH_SHORT).show();

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

        PieDataSet pieDataSet=new PieDataSet(yEntries,"");

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
        legend.isDrawInsideEnabled();
        legend.setEnabled(false);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setXEntrySpace(7);
        legend.setYEntrySpace(5);

        /////create pieData Object

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}
