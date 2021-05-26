package edu.sungshin.univplanner;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListViewAdapter_assignment extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem_assignment> listViewItemList_assignment = new ArrayList<ListViewItem_assignment>() ;
    private HashMap<ListViewItem_assignment, Long> hash_listView_assignment = new HashMap<ListViewItem_assignment, Long>();

    // ListViewAdapter의 생성자
    public ListViewAdapter_assignment() {
    }

    public void sort_hashMap(){
        Log.e("과제 sort 함수 진입","성공");
        //해쉬맵 정렬
        List keySetList = new ArrayList<>(hash_listView_assignment.keySet());

        // 오름차순
        System.out.println("------value 오름차순------");
        Collections.sort(keySetList, (o1, o2) -> (hash_listView_assignment.get(o1).compareTo(hash_listView_assignment.get(o2))));

        for(Object key : keySetList) {
            System.out.println("key : " + key + " / " + "value : " + hash_listView_assignment.get(key));
            ListViewItem_assignment item = (ListViewItem_assignment) key;
            listViewItemList_assignment.add(item);
        }

    }
    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList_assignment.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_assingment, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView d_dayTextView = (TextView) convertView.findViewById(R.id.listview_day);
        TextView assignmentTextView = (TextView) convertView.findViewById(R.id.assignment_name);
        TextView NameTextView = (TextView) convertView.findViewById(R.id.lecture_name) ;
        TextView deadlineTextView = (TextView) convertView.findViewById(R.id.lecture_deadline) ;
        TextView isDoneTextView = (TextView) convertView.findViewById(R.id.assignment_isdone) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem_assignment listViewItem = listViewItemList_assignment.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        d_dayTextView.setText(listViewItem.getD_day());
        assignmentTextView.setText(listViewItem.getAssignmentName());
        NameTextView.setText(listViewItem.getLectureName());
        deadlineTextView.setText(listViewItem.getDeadline());
        isDoneTextView.setText(listViewItem.getIsDone());

        if (listViewItem.getIsDone().compareTo("제출")==0)
            isDoneTextView.setTextColor(Color.parseColor("#0B7903"));
        else
            isDoneTextView.setTextColor(Color.parseColor("#B71C1C"));

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList_assignment.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String d_day, String name, String assignmentName, String deadline, String isDone, long int_Dday) {
        ListViewItem_assignment item = new ListViewItem_assignment();

        item.setD_day(d_day);
        item.setAssignmentName(assignmentName);
        item.setLectureName(name);
        item.setDeadline(deadline);
        item.setIsDone(isDone);

        //listViewItemList.add(item);
        hash_listView_assignment.put(item, int_Dday);
    }
}
