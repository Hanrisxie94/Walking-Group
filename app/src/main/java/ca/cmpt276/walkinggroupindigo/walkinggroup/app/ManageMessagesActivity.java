package ca.cmpt276.walkinggroupindigo.walkinggroup.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import ca.cmpt276.walkinggroupindigo.walkinggroup.R;
import ca.cmpt276.walkinggroupindigo.walkinggroup.dataobjects.Message;
import ca.cmpt276.walkinggroupindigo.walkinggroup.dataobjects.User;
import ca.cmpt276.walkinggroupindigo.walkinggroup.proxy.ProxyBuilder;
import ca.cmpt276.walkinggroupindigo.walkinggroup.proxy.ProxyFunctions;
import ca.cmpt276.walkinggroupindigo.walkinggroup.proxy.WGServerProxy;
import retrofit2.Call;

public class ManageMessagesActivity extends AppCompatActivity {

    private WGServerProxy proxy;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_messages);
        setActionBarText(getString(R.string.manage_messages));
        mUser = User.getInstance();
        proxy = ProxyFunctions.setUpProxy(ManageMessagesActivity.this, getString(R.string.apikey));
        populateMessages();
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {

    }

    private void populateMessages() {
        Call<List<Message>> messageCaller = proxy.getMessages(mUser.getId());
        ProxyBuilder.callProxy(ManageMessagesActivity.this, messageCaller,
                returnedMessages -> populateMessagesListView(returnedMessages));
    }

    private void populateMessagesListView(List<Message> returnedMessages) {
        ArrayAdapter<Message> adapter = new MyListOfMessages(returnedMessages);
        ListView messagesListView = findViewById(R.id.messages_listview);
        messagesListView.setAdapter(adapter);
        new ArrayAdapter<>(this,
                R.layout.messages_layout);
        messagesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        messagesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });
    }

    private class MyListOfMessages extends ArrayAdapter<Message> {
        List<Message> mMessageList;

        public MyListOfMessages(List<Message> messageList) {
            super(ManageMessagesActivity.this, R.layout.messages_layout
                , messageList);
            mMessageList = messageList;
        }

        @NonNull
        @Override
        public  View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if(convertView == null){
                itemView = getLayoutInflater().inflate(R.layout.messages_layout,
                        parent,
                        false);
            }

            Message newMessage;
            User newUser;

            if (mMessageList.isEmpty()) {
                newMessage = new Message();
            }
            else {
                newMessage = mMessageList.get(position);
                itemView.setTag(newMessage.getId());
            }
            if (newMessage.getText() != null) {
                try {
                    newUser = newMessage.getFromUser();
                    TextView context = itemView.findViewById(R.id.message_context);
                    context.setText(newMessage.getText());

                    TextView nameText = itemView.findViewById(R.id.message_header);
                    nameText.setText(newUser.getName());
                } catch (NullPointerException e) {
                    Log.e("Error", e + mMessageList.toString());
                }
            }
            return itemView;
        }
    }

    private void setActionBarText(String title) {
        try {
            getActionBar().setTitle(title);
            getSupportActionBar().setTitle(title);
        } catch (NullPointerException e) {
            getSupportActionBar().setTitle(title);
        }
    }
}
