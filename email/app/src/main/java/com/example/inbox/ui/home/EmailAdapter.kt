import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inbox.R
import com.example.inbox.ui.home.Email

class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: TextView = itemView.findViewById(R.id.avatar)
        val senderName: TextView = itemView.findViewById(R.id.sender_name)
        val sentTime: TextView = itemView.findViewById(R.id.sent_time)
        val emailContent: TextView = itemView.findViewById(R.id.email_content)
        val starIcon: ImageView = itemView.findViewById(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.email_item, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]

        // Thiết lập thông tin cho các view
        holder.avatar.text = email.senderName.first().toString() // Lấy chữ cái đầu tiên
        holder.senderName.text = email.senderName
        holder.sentTime.text = email.sentTime
        holder.emailContent.text = email.content

        // Xử lý hiển thị ngôi sao nếu cần
        if (email.isStarred) {
            holder.starIcon.visibility = View.VISIBLE
        } else {
            holder.starIcon.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return emailList.size
    }
}
