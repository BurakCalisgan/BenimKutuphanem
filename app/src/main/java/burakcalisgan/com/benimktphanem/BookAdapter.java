package burakcalisgan.com.benimktphanem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,R.layout.custom_list_view,null);
        TextView txtBookName =  view.findViewById(R.id.txtBookName);
        TextView txtAuthor =  view.findViewById(R.id.txtAuthor);
        TextView txtPageNumber =  view.findViewById(R.id.txtPageNumber);

        Book book = bookList.get(position);

        txtBookName.setText(book.getBookName());
        txtAuthor.setText(book.getAuthor());
        txtPageNumber.setText(book.getPageNumber());
        view.setTag(bookList.get(position).getBookName());

        return view;
    }
}
