# Tusa

**Tusa** is a smart group organizer that helps friends and teams plan meetups, split bills, and track shared expenses — all without the usual chaos of group chats and spreadsheets.

Whether it's a Friday dinner, a weekend trip, or office coffee runs, Tusa keeps everyone on the same page.

> 🚧 **Status**: Early-stage prototype. Core functionality is under active development.

---

## ✨ Features

- **Party Management** – Create private groups ("parties") for any event or team.
- **Event Scheduling** – Set a time and place for your next meetup.
- **Expense Tracking** – Log shared costs and see who owes whom.
- **Smart Reminders** – Automatic notifications before events and after new bills are added.
- **Telegram Integration** – Works seamlessly as a Telegram Mini App + Bot.

---

## 🧩 Architecture (Microservices)

The project is built as a set of small, independent services that communicate with each other:

- **User Service** – Handles authentication and user profiles.
- **Party Service** – Manages groups and memberships.
- **Event Service** – Stores meetup details (time, location, voting).
- **Billing Service** – Calculates debts and balances.
- **Notification Service** – Sends reminders and alerts via Telegram/email.
- **API Gateway** – Single entry point for the Telegram Mini App.
- **Kafka** – Asynchronous messaging between services (e.g., when a bill is created, Notification Service picks it up).
- **PostgreSQL** – Each service has its own dedicated database.

This approach keeps the system scalable, maintainable, and fun to extend.

---

## 🔗 Live Demo

👉 **[tusa.yourdomain.com]** 

---

## 📄 License

This project is licensed under the **MIT License**.

You are free to use, modify, and distribute the code, even for commercial purposes, as long as you include the original copyright notice.

See the [LICENSE](LICENSE) file for details.

---

## 🙌 Contributing

Not open for contributions just yet — I'm focusing on the initial architecture and core features. But feel free to star the repo and follow along!

---

## 📬 Contact

Questions or ideas? Reach out via [Telegram](https://t.me/waterflod) or open an issue.

---

### 💡 Why the name "Tusa"?

*Tusa* — short, memorable, and sounds like "together" in a few languages. Fits the vibe.