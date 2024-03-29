package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {

	private UUID mId;
	
	private Date mDate;
	
	private boolean mSolved;
	
	private String mTitle;
	
	public Crime() {
		//generate unique Identifier
		mId = UUID.randomUUID();
		mDate = new Date();
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public boolean isSolved() {
		return mSolved;
	}

	public void setSolved(boolean solved) {
		mSolved = solved;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public UUID getId() {
		return mId;
	}
	@Override
	public String toString() {
		return mTitle;
	}

}
