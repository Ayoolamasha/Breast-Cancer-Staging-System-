package com.ayoolamasha.breastcancerstaging.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ayoolamasha.breastcancerstaging.Database.Records;
import com.ayoolamasha.breastcancerstaging.R;

import java.text.DateFormat;
import java.util.Calendar;

public class RecordsAdapter extends ListAdapter<Records, RecordsAdapter.ViewHolder> {


    private OnItemClickListener listener;

    public RecordsAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Records> DIFF_CALLBACK = new DiffUtil.ItemCallback<Records>() {
        @Override
        public boolean areItemsTheSame(@NonNull Records oldItem, @NonNull Records newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Records oldItem, @NonNull Records newItem) {
            return oldItem.getPatientName().equals(newItem.getPatientName()) &&
                    oldItem.getStagingDetails().equals(newItem.getStagingDetails()) &&
                    oldItem.getPatientAge() == newItem.getPatientAge() &&
                    oldItem.getStageFigure() == newItem.getStageFigure();
        }
    };

    @NonNull
    @Override
    public RecordsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.records_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordsAdapter.ViewHolder holder, int position) {
        Records patientRecord = getItem(position);
        holder.patientName.setText(patientRecord.getPatientName());
        holder.patientSymptoms.setText(patientRecord.getStagingDetails());
        holder.patientStage.setText(String.valueOf(patientRecord.getStageFigure()));
        holder.patientAge.setText(String.valueOf(patientRecord.getPatientAge()));
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        holder.dateAdded.setText(currentDate);


    }

    // TO GET THE POSITION TO DELETE A NOTE
    public Records getNoteAt(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView patientName, patientSymptoms, patientStage, dateAdded,
        patientAge;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.dummyPatientName);
            patientSymptoms = itemView.findViewById(R.id.dummySymptoms);
            patientStage = itemView.findViewById(R.id.dummyStage);
            patientAge = itemView.findViewById(R.id.ageTest);
            dateAdded = itemView.findViewById(R.id.testDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }

                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Records records);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;

    }
}
