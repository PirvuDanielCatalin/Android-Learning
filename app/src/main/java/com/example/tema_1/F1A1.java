package com.example.tema_1;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class F1A1 extends Fragment {

    private RecyclerView rvList;
    private CustomAdapter customAdapter;
    private ArrayList<String> array;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f1a1, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText firstName = view.findViewById(R.id.firstname);
        final EditText lastName = view.findViewById(R.id.lastname);
        final UserRepository userRepository = new UserRepository(getContext());
        rvList = view.findViewById(R.id.recycle_view);
        Button btn = view.findViewById(R.id.btn);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Activity2.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }
        Button btn2 = view.findViewById(R.id.btn2);
        if (btn2 != null) {
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fName = firstName.getText().toString();
                    String lName = lastName.getText().toString();
                    if ((!fName.equals("")) && (!lName.equals(""))) {
                        User user = userRepository.getUserTask(fName, lName, new OnUserRepositoryActionListener() {
                            @Override
                            public void actionSuccess() {
                                return;
                            }

                            @Override
                            public void actionFailed() {
                                return;
                            }
                        });
                        if (user == null) {
                            array.add(fName + " " + lName);
                            customAdapter.notifyDataSetChanged();
                            userRepository.insertTask(new User(fName, lName), new OnUserRepositoryActionListener() {
                                @Override
                                public void actionSuccess() {
                                    return;
                                }

                                @Override
                                public void actionFailed() {
                                    return;
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "User-ul exista deja!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Completati campurile !",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        Button btn3 = view.findViewById(R.id.btn3);
        if (btn3 != null) {
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fName = firstName.getText().toString();
                    String lName = lastName.getText().toString();
                    if ((!fName.equals("")) && (!lName.equals(""))) {
                        boolean removed = array.remove(fName + " " + lName);
                        if (removed) {
                            customAdapter.notifyDataSetChanged();
                            User user = userRepository.getUserTask(fName, lName, new OnUserRepositoryActionListener() {
                                @Override
                                public void actionSuccess() {
                                    return;
                                }

                                @Override
                                public void actionFailed() {
                                    return;
                                }
                            });
                            if (user != null) {
                                userRepository.deleteTask(user, new OnUserRepositoryActionListener() {
                                    @Override
                                    public void actionSuccess() {
                                        return;
                                    }

                                    @Override
                                    public void actionFailed() {
                                        return;
                                    }
                                });
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "User-ul nu se afla in BD !",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Completati campurile !",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        setUpRecyclerView();
    }
    private void setUpRecyclerView() {

        UserRepository userRepository = new UserRepository(getContext());
        List<User> users = userRepository.getTask(new OnUserRepositoryActionListener() {
            @Override
            public void actionSuccess() {
                return;
            }

            @Override
            public void actionFailed() {
                return;
            }
        });
        array = new ArrayList<String>();
        for(User user:users){
            array.add(user.getFirstName() + " " + user.getLastName());
        }
        customAdapter = new CustomAdapter(getContext(), array);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        rvList.setLayoutManager(mLayoutManager);
        rvList.setAdapter(customAdapter);
    }
}
