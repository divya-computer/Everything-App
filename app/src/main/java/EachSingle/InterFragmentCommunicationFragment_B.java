package EachSingle;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.everything.R;

public class InterFragmentCommunicationFragment_B extends Fragment
{
    private EditText editText;
    private Button buttonOk;
    private FragmentBListener listener;

    public interface FragmentBListener
    {
        void onInputBSent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.inter_fragment_b , container , false);

        editText = v.findViewById(R.id.edit_text);
        buttonOk = v.findViewById(R.id.button_ok);

        buttonOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CharSequence input = editText.getText();
                listener.onInputBSent(input);

            }
        });

        return v;
    }

    public void updateEditText(CharSequence newText)
    {
        editText.setText(newText);
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);

        if (context instanceof FragmentBListener)
        {
            listener = (FragmentBListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement FragmentBListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;

    }
}
