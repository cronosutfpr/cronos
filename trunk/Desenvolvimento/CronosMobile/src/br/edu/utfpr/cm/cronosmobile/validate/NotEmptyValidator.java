package br.edu.utfpr.cm.cronosmobile.validate;

import android.content.Context;
import br.edu.utfpr.cm.cronosmobile.R;

public class NotEmptyValidator extends AbstractValidator {
	
	private int mErrorMessage = R.string.validator_empty;
	
	
	public NotEmptyValidator(Context c) {
		super(c);
	}
	
	@Override
	public boolean isValid(String value) {
		if(value != null){
			if(value.length() > 0)
				return true;
			else
				return false;
		}else{
			return false;
		}
	}

	@Override
	public String getMessage() {
		return mContext.getString(mErrorMessage);
	}

}
