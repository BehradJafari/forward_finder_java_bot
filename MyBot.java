package learn;


import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by This PC on 8/13/2017.
 */
public class MyBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            if (update.getMessage().getForwardFrom() != null){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("یک پیام فوروارد شده است");
                sendMessage.setChatId(update.getMessage().getChatId());
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rows = new ArrayList<>();
                List<InlineKeyboardButton> row = new ArrayList<>();
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText("id");
                button.setCallbackData("id" + update.getMessage().getForwardFrom().getId());
                row.add(button);
                button = new InlineKeyboardButton();
                button.setText("username");
                button.setCallbackData(button.getText() + update.getMessage().getForwardFrom().getUserName());
                row.add(button);
                rows.add(row);
                row = new ArrayList<>();
                button = new InlineKeyboardButton();
                button.setText("firstname");
                button.setCallbackData(button.getText() + update.getMessage().getForwardFrom().getFirstName());
                row.add(button);
                button = new InlineKeyboardButton();
                button.setText("date");
                button.setCallbackData(button.getText() + update.getMessage().getForwardDate());
                row.add(button);
                rows.add(row);
                row = new ArrayList<>();
                button = new InlineKeyboardButton();
                button.setText("delete");
                button.setCallbackData(button.getText() + update.getMessage().getMessageId());
                row.add(button);
                rows.add(row);
                markup.setKeyboard(rows);
                sendMessage.setReplyMarkup(markup);
                sendMessage.setReplyToMessageId(update.getMessage().getMessageId());
                try {
                    sendMessage(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getNewChatMembers() != null){
                try {
                    User me = getMe();
                    for (User user:update.getMessage().getNewChatMembers()) {
                        if (user.getId().equals(me.getId())){
                            SendMessage sendMessage =new  SendMessage();
                            sendMessage.setText("بسیار خوش آمدم");
                            sendMessage.setChatId(update.getMessage().getChatId());
                            sendMessage.setReplyToMessageId(update.getMessage().getMessageId());
                            sendMessage(sendMessage);

                        }
                    }
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (update.hasCallbackQuery()){
            String data = update.getCallbackQuery().getData();
            if (data.startsWith("delete")){
                Long chatId = update.getCallbackQuery().getMessage().getChatId();
                Integer messageId = Integer.valueOf(data.substring("delete".length()));
                DeleteMessage deleteMessage = new DeleteMessage();
                deleteMessage.setChatId(String.valueOf(chatId));
                deleteMessage.setMessageId(messageId);
                try {
                    deleteMessage(deleteMessage);
                    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
                    answerCallbackQuery.setText("دلیت شد");
                    answerCallbackQuery.setCallbackQueryId(update.getCallbackQuery().getId());
                    answerCallbackQuery(answerCallbackQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (data.startsWith("id")){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId());
                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                sendMessage.setText(data.substring("id".length()));
                try {
                    sendMessage(sendMessage);
                    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
                    answerCallbackQuery.setText("آیدی فرستاده شد");
                    answerCallbackQuery.setCallbackQueryId(update.getCallbackQuery().getId());
                    answerCallbackQuery(answerCallbackQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if (data.startsWith("username")){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId());
                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                sendMessage.setText("@" + data.substring("username".length()));
                try {
                    sendMessage(sendMessage);
                    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
                    answerCallbackQuery.setText("آیدی فرستاده شد");
                    answerCallbackQuery.setCallbackQueryId(update.getCallbackQuery().getId());
                    answerCallbackQuery(answerCallbackQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (data.startsWith("firstname")){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId());
                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                sendMessage.setText(data.substring("firstname".length()));
                try {
                    sendMessage(sendMessage);
                    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
                    answerCallbackQuery.setText("آیدی فرستاده شد");
                    answerCallbackQuery.setCallbackQueryId(update.getCallbackQuery().getId());
                    answerCallbackQuery(answerCallbackQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (data.startsWith("date")){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId());
                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                Date date = new Date(Long.valueOf(data.substring("date".length())));
                sendMessage.setText(date.toLocaleString());
                try {
                    sendMessage(sendMessage);
                    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
                    answerCallbackQuery.setText("آیدی فرستاده شد");
                    answerCallbackQuery.setCallbackQueryId(update.getCallbackQuery().getId());
                    answerCallbackQuery(answerCallbackQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "transferchannelsbot";
    }

    @Override
    public String getBotToken() {
        return "436946078:AAGaAL23RLtX5uEgwcKbYJHc3b-R9Fulu70";
    }
}