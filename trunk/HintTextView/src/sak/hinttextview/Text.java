package sak.hinttextview;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class Text extends EditText implements TextWatcher {

	private boolean required = false;

	public Text(Context context) {
		this(context, null);
	}
	
	public Text(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}
	
	private void initView( Context context, AttributeSet attrs){
		addTextChangedListener( this);
		if (attrs != null) {
			required = attrs.getAttributeBooleanValue(null, "required", false);
			if (this.getHint() == null) {
				this.setHint(R.string.hint_required);
			}
		}
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		isValid();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	
	}
	

	@Override
	protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
		if(! focused){
			isValid();
		}
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
	}
	
	public boolean isValid(){
		String errorMessage = null;
		
		String text = getEditableText().toString();
		
		// 必須チェック
		if (isRequired()) {
			if (text == null || text.equals("")) {
				errorMessage = getResources().getString( R.string.error_required);			
			}
		}

		boolean result = false;
		if (errorMessage != null && !errorMessage.equals("")) {
			setError(errorMessage);
		}
		else{
			setError(null);
			result = true;
		}
		
		return result;
	}

	public boolean isRequired() {
		return required;
	}
	
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

}
