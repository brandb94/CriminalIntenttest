package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class CrimeFragment extends Fragment {
	public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";
	private static final String DIALOG_DATE = "date";
	private static final int REQUEST_DATE = 0;
	
	private Crime mCrime;
	private EditText mTitleField;
	
	private Button mDateButton;
	private CheckBox mSolvedCheckBox;

	public static CrimeFragment newInstance(UUID crimeID) {
		
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CRIME_ID, crimeID);
		
		CrimeFragment fragment = new CrimeFragment();
		
		fragment.setArguments(args);
		return fragment;
	}
	
	
	
	private void updateDate() {
		mDateButton.setText(mCrime.getDate().toString());
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
//		if (requestCode != Activity.RESULT_OK) return;
//		if (requestCode == REQUEST_DATE) {
//			Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
//			mCrime.setDate(date);
//			updateDate();
		//}
		if (resultCode != Activity.RESULT_OK) return;
		if (requestCode == REQUEST_DATE) {
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mCrime.setDate(date);
			updateDate();
			//mDateButton.setText(mCrime.getDate().toString());
		}
	}





	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//mCrime = new Crime();
		//UUID crimeID = (UUID)getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
		UUID crimeID = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.get(getActivity()).getCrime(crimeID);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState)  {
		View v = inflater.inflate(R.layout.fragment_crime, parent, false);
		
		mTitleField = (EditText)v.findViewById(R.id.crime_title);
		mTitleField.setText(mCrime.getTitle());
		mTitleField.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mCrime.setTitle(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		mDateButton = (Button)v.findViewById(R.id.crime_date);
		updateDate();
		//mDateButton.setEnabled(false);
		mDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				//DatePickerFragment dialog = new DatePickerFragment();
				DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
				dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);//need?
				dialog.show(fm, DIALOG_DATE);
			}
		});
		
		mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
		mSolvedCheckBox.setChecked(mCrime.isSolved());
		mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mCrime.setSolved(isChecked);
			}
		});

		return v;
	}



}
