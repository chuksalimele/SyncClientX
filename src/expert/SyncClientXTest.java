/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expert;

import expert.trade.*;
import expert.ExpertAdvisorMQ4;
import java.util.LinkedList;
import javax.swing.*;
import net.SyncTradeConnector;
import static expert.SyncClientXTest.ExitClearanceFactor.*;
import static expert.SyncClientXTest.TradeMode.*;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author user
 */
public class SyncClientXTest extends ExpertAdvisorMQ4 {

    String VERSION = "13.0.0";

    int COPIED_TRADE_MAGIC_NUMBER = 114455;

    SyncTradeConnector stc = new SyncTradeConnector();

    int ExtConnection = -1;

    int UNDEFINED = -1;

    public class Printer {

        private String onceData;
        private String startId;
        private String endId;

        // Constructor
        public Printer() {
            init();  // Call init to initialize variables
        }

        // Initialization method
        public void init() {
            onceData = "";
            startId = "";
            endId = "";
        }

        // Print data only once (if different from the previous)
        public void printOnce(String data) {
            if (!onceData.equals(data)) {
                System.out.println(data);
                onceData = data;
            }
        }

        // Print data if start and end IDs are different
        public void print(String data) {
            if (!startId.equals(endId)) {
                System.out.println(data);
            }
        }

        // Overloaded start method (String parameter)
        public void start(String startId) {
            this.startId = startId;
        }

        // Overloaded end method (String parameter)
        public void end(String endId) {
            this.endId = endId;
        }

        // Overloaded start method (int parameter)
        public void start(int startId) {
            this.startId = Integer.toString(startId);
        }

        // Overloaded end method (int parameter)
        public void end(int endId) {
            this.endId = Integer.toString(endId);
        }
    }

    @Override
    public void OnTrade() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void OnTradeTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    enum ExitClearanceFactor {
        _0_PERCENT,
        _30_PERCENT,
        _50_PERCENT,
        _80_PERCENT,
        _100_PERCENT,
    };

    class VirtualSync {

        long own_ticket;

        long peer_ticket;

        double peer_stoploss;

        double peer_spread_point;

        boolean IsHitPeerStoploss;

    }

    boolean SyncCopyManualEntry = false;// Sync copy manual entry
    ExitClearanceFactor exitClearanceFactor = _30_PERCENT;// Exit clearance factor
    boolean OnlyTradeWithCredit = false;// Only trade with credit.

    enum TradeMode {
        PACKET,
        LIVE
    };

//int ExtConnection=-1;
    class MarketPrice {

        String symbol;
        double bid;
        double ask;
    };

    class TradePacket {

        String command;
        String command_id;
        String action;
        String uuid;
        String force;
        String reason;
        String origin_ticket;

        String immediate;

        String peer_broker;
        String peer_account_number;

        long own_ticket;
        long peer_ticket;
        double peer_stoploss;
        double peer_spread_point;

        String symbol;
        long ticket;
        String position;
        double lot_size;
        double open_price;
        long signal_time;
        long close_time;
        long open_time;
        double target;//target price
        double stoploss;//stoploss price
        double spread_point;
        String copy_type;

        double floating_balance;
        double account_balance;

        double partial_closed_lot_fraction;

        String sync_state_paird_id;
    };

    class ChangeStats {

        boolean TradeCountChanged;
        boolean TradeCountIncreased;
        boolean TradeModified;
        boolean TradeSwapChanged;

    };

    String Host = "localhost";
    int Port = 4000;
    double EXIT_CLEARANCE_FACTOR = 0.3;
    MarketPrice[] prices = new MarketPrice[47];
    String PRICE_PIPE_PATH = "\\\\.\\pipe\\sync_trades_pipe";
    boolean isConnectionOpen = false;
    final String NEW_LINE = "\n";
    final String TAB = "\t";
    double CumStoploss = 0;
    double CumTarget = 0;
    double CumSwap = 0;
    int BuyCount = 0;
    int BuyLimitCount = 0;
    int BuyStopCount = 0;
    int SellCount = 0;
    int SellLimitCount = 0;
    int SellStopCount = 0;
    int HistoryTotal = 0;
    String UnusedRecv = "";
    boolean PrintConnectionWaiting = true;
    boolean PrintEAIsStopped = true;
    final String PING_PACKET = "ping=pong";
    long ticketsOfSyncCopy[];
    long ticketsOfSyncClose[];
    long ticketsOfSyncModify[];
    boolean IsIntroRequired = true;
    int RUN_INTERVAL = 200;
    boolean IsTimerRunning = false;
    boolean IsMarketClosed = false;
    boolean IsMarketJustOpen = false;
    long lastKnownServerTime = 0;
    int lastErrorCode = 0;
    long ticketsOfPlacementOrder[];
    long HistoryFromTime = 0;
    double MyAccountBalance = 0;
    double ExpectedHedgeProfit = 0;
    double ExpectedHedgeProfitTomorrow = 0;
    double AccountSwapPerDay = 0;
    double AccountTradeCost = 0;
    double AccountSwapCost = 0;
    double AccountCommissionCost = 0;
    boolean WarnTickValueModified = false;
    boolean IsInitialSpreadFound = false;
    int InitialSpreadTickCount = 0;
    double ExitSpreadPoint = 0;
    double PreSpreadPoint = 0;
    long ExitSpreadLastTime = 0;
    int NtTradeCount = 0;
    double SpreadPointSum = 0;
    int SpreadTickCount = 0;
    boolean Terminating = false;
    boolean IsTerminated = false;
    double SpreadPoint = 0;
    double LastAutoModifiedTarget = 0;
    double PrevTickValue = 0;
    double TickValueVariance = 0.00001;
    double MAX_TICK_VALUES_VARIANCE = 0.001;
    boolean IsExitAtPeerStoplossEnabled = false;

    class PacketOrder {

        String position;
        String symbol;
        long ticket;
        double open_price;
        double close_price;
        long open_time;
        double close_time;
        double lot_size;
        double target;
        double stoploss;
        long magic;
        double commission;

    };

    JTrade tradeObj = new JTrade();                      // trading object
    JAccountInfo accountObj = new JAccountInfo();                     // symbol info object
    JSymbolInfo symbolObj = new JSymbolInfo();                     // symbol info object
    JOrderInfo orderObj = new JOrderInfo();                      // trade order object
    JHistoryOrderInfo historyOrderObj = new JHistoryOrderInfo();                      // trade order object
    JPositionInfo positionObj = new JPositionInfo();                   // trade position object

    LinkedList vSyncList = new LinkedList<VirtualSync>();

    double ExpectedExitProfit = 0; // profit if exit at peer stoploss
    double ExpectedTargetProfit = 0;// profit if exit at main target
    double ExpectedExitBalance = 0;// balance if exit at peer stoploss
    double ExpectedTargetBalance = 0;// balance if exit at main target

    String SyncStatePairID;

    long lastPingTime = 0;
    int PING_INTERVAL_IN_MINUTES = 15;

    int MAX_ALLOW_TERMINAL_DISCONNECTED_MINUTE = 1; //TODO - configurable from the app gui

    int WorkingPosition = -1;

    String strRuning = "EA Running...";

    int fialReadCount = 0;

    boolean debugPriceIsClosePrice = false;
    int bugResolved = 0;

    int PeerRealSymbolDigits = UNDEFINED; //We need this information to make sure both own and peer use the same symbol digit which ever is smaller

    double PeerAccountMargin;
    double PeerStopoutLevel;
    double PeerAccountBalance;
    double PeerAccountCredit;
    double PeerTotalCommission;
    double PeerTotalLotSize;
    double PeerContractSize;
    double PeerPosition;
    double PeerBaseOpenPrice;
    double PeerTotalSwap;
    double PeerSafetySpread;

    double TotalCommission = 0;
    double TotalLotSize = 0;
    double TotalSwap = 0;
    String Position = "";

    String SymbolForMarginReqirement = "";

    long lastConnectionAvailsbleTime = 0;
    boolean isWillRestartTerminalSent = false;
    boolean isPrintAboutToRestart = false;

    Printer buysltpPrinter = new Printer();
    Printer sellsltpPrinter = new Printer();

    JDialog dialog = new JDialog();
    JList lstPeerTicketsView = new JList();
    JButton btn = new JButton();
    JLabel lblBalance = new JLabel();
    JLabel labelBalance = new JLabel();
    JLabel lblExpectedProfitRange = new JLabel();
    JLabel labelExpectedProfitRange = new JLabel();
    JLabel lblExpectedBalanceRange = new JLabel();
    JLabel labelExpectedBalanceRange = new JLabel();

    JLabel labelExitDescription = new JLabel();
    JLabel lblSymbol = new JLabel();
    JLabel lblPeerStoploss = new JLabel();
    JLabel lblActualExit = new JLabel();
    JLabel labelActualExit = new JLabel();
    JLabel labelLessPeerSpread = new JLabel();

    JLabel lblAlert = new JLabel();

    JPanel panelTop = new JPanel();
    JPanel panelCenter = new JPanel();
    JPanel panelBottom = new JPanel();

    LinkedList OpenTicketList = new LinkedList(); //any enlisted ticket that is not found by the positionObj is assumed to be closed

    /*input*/ boolean EnableManualStoplossAdjustion = false;  // TESTING!!! EnableManualStoplossAdjustion

//+------------------------------------------------------------------+
    void computeStoploss() {

        if (EnableManualStoplossAdjustion) {
            return;
        }

        if (OrdersTotal() == 0) {
            LastAutoModifiedTarget = 0;
            return;
        }

        double StopLossAtStopOut = 0;

        int total_orders = OrdersTotal();
        String symbol = "";
        double total_lots = 0;
        double total_commission = 0;
        double total_swap = 0;
        double open_price = 0;
        for (int i = 0; i < total_orders; i++) {

            if (!OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                return; //just leave - no room for error
            }

            if (i == 0) {//just use the open price of the first order - that is the best we can do
                open_price = OrderOpenPrice();
            }

            symbol = OrderSymbol();
            total_lots += OrderLots();
            total_commission += OrderCommission();
            total_swap += OrderSwap();

        }

        double BuyStopLossAtStopOut = determinePriceAtOwnStopout(OP_BUY, open_price, symbol, total_lots, total_commission, total_swap);
        double SellStopLossAtStopOut = determinePriceAtOwnStopout(OP_SELL, open_price, symbol, total_lots, total_commission, total_swap);

        if (BuyStopLossAtStopOut == open_price || SellStopLossAtStopOut == open_price) {
            //This is possible in some symbols of certain broker e.g HK50 in Blueberry
            //where the symbol tick value is zero most of the time
            return;
        }

        for (int i = 0; i < total_orders; i++) {

            if (!OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                return; //just leave - no room for error
            }

            int SymbolDigits = ensureSameSymboDigitsWithPeer();

            if (SymbolDigits == UNDEFINED) {
                return;
            }

            double order_lots = OrderLots();// chuks - added to avoid strange division by zero - see comment below 

            if (order_lots == 0)// chuks - added to avoid strange division by zero - see comment below 
            {
                return;
            }

            /*@Deprecated - replace by if block below
         if(order_lots * 1000 < AccountBalance()/1000){
           return; //skip since the lot size is too small
        }*/
            if (order_lots < SymbolInfoDouble(OrderSymbol(), SYMBOL_VOLUME_MIN)) {
                return; //skip since the lot size is too small
            }

            if (((OrderType() == OP_BUY))) {
                WorkingPosition = OP_BUY;

                //NOTE: in the case of BUY position NO NEED TO compensate for exit spread since the exit price is at BID price
                //so we always expect the stoploss price to be hit. Note this is not the case for SELL side which can cause 
                //premature hunting of the stoploss price since the Ask price is hit which is before the actual stoploss
                StopLossAtStopOut = BuyStopLossAtStopOut;

                if (IsMarketJustOpen || NormalizeDouble(StopLossAtStopOut, SymbolDigits) != NormalizeDouble(OrderStopLoss(), SymbolDigits)) {
                    if (OrderModify(OrderTicket(), OrderOpenPrice(), StopLossAtStopOut, OrderTakeProfit(), 0)) {
                        sendData(stoplossPacket(StopLossAtStopOut));
                    } else {
                        String error = ErrorDescription(GetLastError());
                        if (error.equals("market is closed")) {
                            IsMarketClosed = true;
                        }
                    }
                }
            }

            if (((OrderType() == OP_SELL))) {
                WorkingPosition = OP_SELL;

                StopLossAtStopOut = SellStopLossAtStopOut;
                if (IsMarketJustOpen || NormalizeDouble(StopLossAtStopOut, SymbolDigits) != NormalizeDouble(OrderStopLoss(), SymbolDigits)) {
                    if (OrderModify(OrderTicket(), OrderOpenPrice(), StopLossAtStopOut, OrderTakeProfit(), 0)) {
                        sendData(stoplossPacket(StopLossAtStopOut));
                    } else {
                        String error = ErrorDescription(GetLastError());
                        if (error.equals("market is closed")) {
                            IsMarketClosed = true;
                        }
                    }
                }
            }

        }

        WriteComment(open_price, StopLossAtStopOut, symbol, total_lots, total_commission, total_swap);

    }

    void computeTakeProfit() {

        TradePacket trade = new TradePacket();

        int total_orders = OrdersTotal();

        double safety_spread_point = PeerSafetySpread * Point();

        for (int i = 0; i < total_orders; i++) {

            if (!OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                return; //just leave - no room for error
            }

            trade.ticket = OrderTicket();
            trade.symbol = OrderSymbol();
            trade.signal_time = TimeCurrent(); //come back

            int SymbolDigits = ensureSameSymboDigitsWithPeer();

            if (SymbolDigits == UNDEFINED) {
                return;
            }

            String data = "";

            if (PeerPosition == OP_BUY) {
                double TargetAtPeerStopOut = determinePriceAtPeerStopout() + safety_spread_point;

                if (NormalizeDouble(TargetAtPeerStopOut, SymbolDigits) != NormalizeDouble(OrderStopLoss(), SymbolDigits)) {
                    if (OrderModify(OrderTicket(), OrderOpenPrice(), OrderStopLoss(), TargetAtPeerStopOut, 0)) {
                        trade.target = TargetAtPeerStopOut;
                        LastAutoModifiedTarget = TargetAtPeerStopOut;
                        data += modifyTakeProfitSuccessPacket(true, trade, "");
                    } else {
                        String error = ErrorDescription(GetLastError());
                        data += modifyTakeProfitSuccessPacket(false, trade, error);
                    }
                }
            }

            if (PeerPosition == OP_SELL) {
                double TargetAtPeerStopOut = determinePriceAtPeerStopout() - safety_spread_point;
                if (NormalizeDouble(TargetAtPeerStopOut, SymbolDigits) != NormalizeDouble(OrderStopLoss(), SymbolDigits)) {
                    if (OrderModify(OrderTicket(), OrderOpenPrice(), OrderStopLoss(), TargetAtPeerStopOut, 0)) {
                        trade.target = TargetAtPeerStopOut;
                        LastAutoModifiedTarget = TargetAtPeerStopOut;
                        data += modifyTakeProfitSuccessPacket(true, trade, "");

                    } else {
                        String error = ErrorDescription(GetLastError());
                        data += modifyTakeProfitSuccessPacket(false, trade, error);
                    }
                }
            }

            if (!"".equals(data)) {
                sendData(data);
            }
        }

    }

//--------------------------------------------------------------------------------------------------
//Get the base open price. That is the position with mininum open price for BUY and maximum for SELL
//---------------------------------------------------------------------------------------------------
    double baseOpenPrice() {

        int total_orders = OrdersTotal();

        //base open price is the lowest of the trade for BUY and the Highest for SELL
        double base_buy_open_price = INT_MIN;
        double base_sell_open_price = INT_MAX;
        for (int i = 0; i < total_orders; i++) {

            if (!OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                return 0; //just leave - no room for error
            }

            if (OrderType() == OP_BUY) {
                //get the maximum
                if (OrderOpenPrice() > base_buy_open_price) {
                    base_buy_open_price = OrderOpenPrice();
                }

            } else if (OrderType() == OP_SELL) {
                //get the mininum
                if (OrderOpenPrice() < base_sell_open_price) {
                    base_sell_open_price = OrderOpenPrice();
                }
            } else {
                return 0;
            }

        }

        if (OrderType() == OP_BUY) {
            return base_buy_open_price;
        } else if (OrderType() == OP_SELL) {
            return base_sell_open_price;
        } else {
            return 0;
        }
    }

//-------------------------------------------------------------------------
//Suppose there are more than one position that are of different
//open price, he function calculate the number of pips 
//away from the first position (the base open price)
//-------------------------------------------------------------------------
    double pipsPointDriftPerLot(double base_open_price) {

        double total_drift = 0;
        int total_orders = OrdersTotal();
        double total_lots = 0;

        for (int i = 0; i < total_orders; i++) {

            if (!OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                return 0; //just leave - no room for error
            }

            total_drift += OrderLots() * MathAbs(OrderOpenPrice() - base_open_price);
            total_lots += OrderLots();

        }

        double drift_per_lot = total_drift / total_lots;

//Print("base_open_price ",base_open_price," total_drift ", total_drift, " total_orders ", total_orders," drift_per_lot ",drift_per_lot);     
        return drift_per_lot;
    }

    double getTotalLotSize() {

        int total_orders = OrdersTotal();
        double total_lots = 0;

        for (int i = 0; i < total_orders; i++) {

            if (!OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                return 0; //just leave - no room for error
            }

            total_lots += OrderLots();

        }

        return total_lots;
    }

    double determinePriceAtOwnStopout(double pos, double open_price, String symbol, double total_lots, double total_commission, double total_swap) {

        double contract_size = MarketInfo(symbol, MODE_LOTSIZE);
        double base_open_price = baseOpenPrice();

        return determineStopout(AccountBalance(),
                AccountCredit(),
                total_commission,
                total_swap,
                AccountMargin(),
                PeerStopoutLevel,
                getTotalLotSize(),
                contract_size,
                pos,
                base_open_price);

    }

    double determinePriceAtPeerStopout() {

        double base_open_price = PeerBaseOpenPrice;

        if (!isRegularSymbol(Symbol())) {
            base_open_price = baseOpenPrice();

            Print("IMPORTANT NOTICE: " + Symbol() + " is a non-regular pair so " + base_open_price
                    + " as open price on this broker was used for take profit computation"
                    + " since price system of similar non-reguar symbols on different"
                    + " brokers may be vary greatly");
        }

        return determineStopout(PeerAccountBalance,
                PeerAccountCredit,
                PeerTotalCommission,
                PeerTotalSwap,
                PeerAccountMargin,
                PeerStopoutLevel,
                PeerTotalLotSize,
                PeerContractSize,
                PeerPosition,
                base_open_price);

    }

    double determineStopout(double account_balance,
            double account_credit,
            double total_commission,
            double total_swap,
            double account_margin,
            double stopout_level,
            double total_lot_size,
            double contract_size,
            double position,
            double base_open_price) {

        if (isGBPJPY()) {
            contract_size = contract_size / 100;
            Print("IMPORTANT NOTICE: " + Symbol() + " contract size is divided by 100 for correct SL and TP");
        }

        double A = account_margin * stopout_level / 100;
        double B = account_balance + account_credit + total_commission + total_swap;
        double C = MathAbs(B - A);//checking first
        double D = total_lot_size * contract_size;
        double E = C / D;

        double stoploss = 0;

        if (position == OP_BUY) {
            stoploss = base_open_price - E;
        } else if (position == OP_SELL) {
            stoploss = base_open_price + E;
        }

        String strPos = position == OP_BUY ? "BUY" : "SELL";

        if ("BUY".equals(strPos)) {
            
            buysltpPrinter.start(strPos);
            buysltpPrinter.print("-------------------------------------------------- ");
            buysltpPrinter.print(strPos + " -> A= " + A);
            buysltpPrinter.print(strPos + " -> B= " + B);
            buysltpPrinter.print(strPos + " -> C= " + C);
            buysltpPrinter.print(strPos + " -> D= " + D);
            buysltpPrinter.print(strPos + " -> E= " + E);
            buysltpPrinter.print(strPos + " -> base_open_price= " + base_open_price);
            buysltpPrinter.print(strPos + " -> stoploss= " + stoploss);
            buysltpPrinter.print("-------------------------------------------------- ");
            buysltpPrinter.end(strPos);
        } else {
            
            sellsltpPrinter.start(strPos);
            sellsltpPrinter.print("-------------------------------------------------- ");
            sellsltpPrinter.print(strPos + " -> A= " + A);
            sellsltpPrinter.print(strPos + " -> B= " + B);
            sellsltpPrinter.print(strPos + " -> C= " + C);
            sellsltpPrinter.print(strPos + " -> D= " + D);
            sellsltpPrinter.print(strPos + " -> E= " + E);
            sellsltpPrinter.print(strPos + " -> base_open_price= " + base_open_price);
            sellsltpPrinter.print(strPos + " -> stoploss= " + stoploss);
            sellsltpPrinter.print("-------------------------------------------------- ");
            sellsltpPrinter.end(strPos);
        }

        return stoploss;
    }

    boolean isGBPJPY() {

        if (!isRegularSymbol(Symbol())) {
            return false;
        }
        String symbol = Symbol();
        //StringToUpper(symbol);
        symbol = symbol.toUpperCase();

        if (StringFind(symbol, "GBPJPY") != -1
                || StringFind(symbol, "GBP/JPY") != -1) {
            return true;
        }

        return false;
    }

    int ensureSameSymboDigitsWithPeer() {

        int OwnSymbolDigits = (int) MarketInfo(Symbol(), MODE_DIGITS);

        if (PeerRealSymbolDigits == UNDEFINED) {
            return UNDEFINED;
        } else if (PeerRealSymbolDigits < OwnSymbolDigits) {
            return PeerRealSymbolDigits; // using the smaller digit
        } else {
            return OwnSymbolDigits;
        }

    }

    //@Deprecated
    double determinePriceAtStopout_OLD(double pos, double open_price, String symbol, double total_lots, double total_commission, double total_swap) {

        //BORROW IDEA HERE  
        double margin = AccountMargin();
        double stopout_margin = margin * AccountStopoutLevel() / 100;
        double stopout_loss = AccountBalance() + AccountCredit() + total_commission + total_swap - stopout_margin;
        double stopout_pip_move = ammountToPips(stopout_loss, total_lots, symbol);
        double stopout_points_move = stopout_pip_move * getUsableSymbolPoint(symbol);

        double base_open_price = baseOpenPrice();

        double pips_point_drift_per_lot = pipsPointDriftPerLot(base_open_price);

//Print("pips_point_drift_per_lot ", pips_point_drift_per_lot);
        double stoploss = 0;

        if (pos == OP_BUY) {
            stoploss = base_open_price - stopout_points_move - pips_point_drift_per_lot;
        } else if (pos == OP_SELL) {
            stoploss = base_open_price + stopout_points_move + pips_point_drift_per_lot;
        }

        return stoploss;
    }

    //@Deprecated
    double determinePriceAtStopout_OLDER(double pos, double open_price, String symbol, double total_lots, double total_commission, double total_swap) {
        double margin = AccountMargin();
        double stopout_margin = margin * AccountStopoutLevel() / 100;
        double stopout_loss = AccountBalance() + AccountCredit() + total_commission + total_swap - stopout_margin;
        double stopout_pip_move = ammountToPips(stopout_loss, total_lots, symbol);
        double stopout_points_move = stopout_pip_move * getUsableSymbolPoint(symbol);
        double stoploss = 0;

        if (pos == OP_BUY) {
            stoploss = open_price - stopout_points_move;
        } else if (pos == OP_SELL) {
            stoploss = open_price + stopout_points_move;
        }

        return stoploss;
    }

    double ammountToPips(double amount, double lots, String symbol) {

        double syb_tick_value = symbolTickValue(symbol); // MAY NOT BE NEEDED HERE
        // SEE HACK SOLUTION BELOW

        if (syb_tick_value == 0) {
            //This is possible in some symbols of certain broker e.g HK50 in Blueberry
            return 0;
        }

        syb_tick_value = 1; //This is a just hack solution. 
        //Since we are using getUsuableSymbolPoint
        //where the Tick size is Divided by the tick value it is
        //reasonable to set the tick value to 1 for all symbols in
        //just the case 

        double contract_size = MarketInfo(Symbol(), MODE_LOTSIZE);
        double tick_size = SymbolInfoDouble(Symbol(), SYMBOL_TRADE_TICK_SIZE);
        double tick_value = SymbolInfoDouble(Symbol(), SYMBOL_TRADE_TICK_VALUE);

        double multiplier = contract_size * tick_size;

        return amount / (lots * multiplier);
    }

    double symbolTickValue(String symbol) {
        double value = MarketInfo(symbol, MODE_TICKVALUE);

        //NOTE: Because of a weird observation where the Tick Value changes very slightly
        //but approximately the same leading to unnecessary computation of Stoploss
        //and resulting in frequent triggering of stoploss_changed events we will simply return the
        //the last known tick value if the difference with the current tick value is negligibe
        //such as 0.00001
        double diff = MathAbs(value - PrevTickValue);

        if (PrevTickValue > 0 && diff <= TickValueVariance) {
            value = PrevTickValue;
        } else if (PrevTickValue > 0 && TickValueVariance < MAX_TICK_VALUES_VARIANCE) {

            double prevTickValueVar = TickValueVariance;
            TickValueVariance *= 10;

            PrintFormat("INCREASE TICK VALUES VARIANCE FROM %f to %f TO PREVENT FREQUENT STOPLOSS CHANGES", prevTickValueVar, TickValueVariance);

            if (diff <= TickValueVariance) {
                value = PrevTickValue;
            }
        } else if (PrevTickValue > 0) {
            PrintFormat("ATTENTON NEEDED!!! RISK OF TOO FREQUENT STOPLOSS CHANGES DUE TO LARGE TICK VALUES VARIANCE WHICH MAY OVERWHELM BROKER SERVER. PLEASE REVIEW CODE!");
            PrintFormat("CODE REVIEW NECESSARY. CONSIDER INCREASING THE TICK VALUES VARIANCES IF POSSIBLE OR NECCESSARY");
        }

        PrevTickValue = value;

        /* //@Deprecated - causes problem for NAS100 - stoploss is incorrect
   if(value <= 0.1){
      value *= 10;
      if(!WarnTickValueModified){
         String WarningMsg = StringFormat("WARNING!!! EA has modified Ticket value of %s from %f to %f as what it expects it to be. Please verify correctness for yourself.", symbol ,value/10.0, value);
         lblAlert.setText(WarningMsg);
         Print(WarningMsg);
         WarnTickValueModified = true;
      }
   }
         */
        return value;
    }

    void WriteComment(double open_price, double stop_loss, String symbol, double total_lots, double total_commission, double total_swap) {

        double symbol_point = getUsableSymbolPoint(symbol);

        Comment("MARGIN: ", AccountMargin(),
                "\nSTOPOUT: ", AccountStopoutLevel(),
                "\nLEVERAGE: ", AccountLeverage(),
                "\nACCOUNT BALANCE: ", AccountBalance(),
                "\nCREDIT VALUE:", AccountCredit(),
                "\nTOTAL SWAP: ", total_swap,
                "\nTICK VALUE: ", symbolTickValue(symbol),
                "\nSPREAD: ", MarketInfo(symbol, MODE_SPREAD),
                "\nUSING EXIT SPREAD: ", (int) (ExitSpreadPoint / symbol_point),
                "\nTOTAL LOT SIZE: ", total_lots,
                "\nTOTAL COMMISSION: ", total_commission,
                "\nFIRST ORDER OPEN PRICE: ", open_price,
                "\nORDER STOPLOSS: ", stop_loss);

    }

    String stoplossPacket(double stoploss) {

        double symbol_point = getUsableSymbolPoint(OrderSymbol());

        return "ticket=" + OrderTicket() + TAB
                + "stoploss_change_time=" + (long) TimeCurrent() + TAB
                + "stoploss_changed=true" + TAB
                + "point=" + symbol_point + TAB
                + "digits=" + SymbolInfoInteger(OrderSymbol(), SYMBOL_DIGITS) + TAB
                + "stoploss=" + stoploss + TAB;

    }

//+------------------------------------------------------------------+
//| Expert initialization function                                   |
//+------------------------------------------------------------------+
    public int OnInit() {

        if (!IsExpertEnabled()) {
            MessageBox("Failed to start EA becacuse it is not yet enabled!\n\nHint: Click on Auto Trading on client terminal", "FAILED", MB_ICONERROR);
            return INIT_FAILED;
        }

        if (!IsDllsAllowed()) {
            MessageBox("This EA uses DLL but DLL is not yet enabled!\n\nHint: Click on 'Allow DLL imports' on Expert Properties dialog", "FAILED", MB_ICONERROR);
            return INIT_FAILED;
        }

        if (!IsTradeAllowed()) {
            MessageBox("Live trading is not enabled. Please enable Allow Live Trading!\n\nHint: Ensure Allow Live Trading checkbox is checked on EA properties dialog!", "FAILED", MB_ICONERROR);
            return INIT_FAILED;
        }

        if (exitClearanceFactor == _0_PERCENT) {
            EXIT_CLEARANCE_FACTOR = 0;
        } else if (exitClearanceFactor == _30_PERCENT) {
            EXIT_CLEARANCE_FACTOR = 0.3;
        } else if (exitClearanceFactor == _50_PERCENT) {
            EXIT_CLEARANCE_FACTOR = 0.5;
        } else if (exitClearanceFactor == _80_PERCENT) {
            EXIT_CLEARANCE_FACTOR = 0.8;
        } else if (exitClearanceFactor == _100_PERCENT) {
            EXIT_CLEARANCE_FACTOR = 1;
        }

        MyAccountBalance = AccountBalance();

        //--- create timer
        RunTimerIfNot();

        //clear previous comments
        Comment("");

        if (SyncCopyManualEntry) {
            MessageBox("You have chosen to sync copy manual entries.\nMake sure the pairing EA is also set to do the same otherwise manual entries will not sync copy.", "ATTENTION!!!", MB_ICONEXCLAMATION);
        }

        SwingUtilities.invokeLater(() ->creatGUI());

        if (!validateExchangeRateSymbol()) {
            MessageBox("Failed to start EA becacuse " + SymbolForMarginReqirement + " price which is required internally could not be determined. Kindly ensure a chart of " + SymbolForMarginReqirement + " is currently loaded on the trading platform!", "FAILED", MB_ICONERROR);
            return INIT_FAILED;
        }

        return (INIT_SUCCEEDED);
    }

    boolean validateExchangeRateSymbol() {

        if (!isRegularSymbol(Symbol())) {
            SymbolForMarginReqirement = Symbol();
            return iClose(SymbolForMarginReqirement, 0, 0) != 0;
        }

        String symbol = Symbol();
        int len = StringLen(symbol);

        int prefix_index = -1;
        int suffix_index = -1;
        boolean has_slash = false;
        int prefix_count = 0;

        for (int i = 0; i < len; i++) {

            char c = StringGetChar(symbol, i);

            if (c == '/') {
                has_slash = true;
            }

            if (c == '.' && prefix_index == -1) {
                prefix_index = i;
                prefix_count++;
            } else if (c == '.' && prefix_index != -1) {
                suffix_index = i;
                prefix_count++;
            }

        }

        if (prefix_count == 1 && prefix_index >= 6) {
            suffix_index = prefix_index;
            prefix_index = -1;
        }

        int base_currency_index = prefix_index + 1;
        int quote_currency_index = base_currency_index + 3;

        if (has_slash) {
            quote_currency_index++;
        }

        String base_currency = StringSubstr(symbol, base_currency_index, 3);
        String quote_currency = StringSubstr(symbol, quote_currency_index, 3);

        //StringToUpper(up_case_base_currency);
        String up_case_base_currency = base_currency.toUpperCase();

        if (up_case_base_currency.equals(AccountCurrency())) {
            //e.g USDJPY if Account currency is USD
            SymbolForMarginReqirement = Symbol();
            return iClose(SymbolForMarginReqirement, 0, 0) != 0;
        }

        //At this point the base currency is not the AccountCurrency() usually USD
        //Now replace the quote currency with the Account Currency 
        //char symbol_arr[];
        //StringToCharArray(symbol, symbol_arr);
        char[] symbol_arr = symbol.toCharArray();
        char quote_currency_arr[];
        //StringToCharArray(AccountCurrency(), quote_currency_arr);
        quote_currency_arr = AccountCurrency().toCharArray();

        for (int i = 0; i < 3; i++) {
            symbol_arr[i + quote_currency_index] = quote_currency_arr[i];
        }

        symbol = CharArrayToString(symbol_arr);

        SymbolForMarginReqirement = symbol;
        if (iClose(symbol, 0, 0) != 0) {
            return true;
        }

        //lets try symbols without prefix
        int start_index = prefix_index + 1;
        int end_index = has_slash ? 7 : 6;
        symbol = StringSubstr(symbol, start_index, end_index);

        SymbolForMarginReqirement = symbol;
        if (iClose(symbol, 0, 0) != 0) {
            return true;
        }

        return false;
    }

    void startUpEA(String init_msg) {

        //String init_msg = "Initializing EA...";
        if (init_msg != null) {
            Print(init_msg);
            SwingUtilities.invokeLater(() -> 
                lblAlert.setText(init_msg)
            );
        }

        //initControlVariables(); NO NEED
        HistoryTotal = OrdersHistoryTotal();

        sendIntro();
        sendDataAttrForSyncStateID();
        sendSyncOrdersData();

    }

    void sendIntro() {

        //String ea_executable_file = StringSubstr(__PATH__, 0, StringLen(__PATH__) - 3) + "ex" + StringSubstr(__PATH__, StringLen(__PATH__) - 1);
        String ea_executable_file = __PATH__();//in Java
        
        String data = "intro=true" + TAB
                + "version=" + VERSION + TAB
                + "is_live_account=" + !IsDemo() + TAB
                + "broker=" + AccountCompany() + TAB
                + "account_number=" + AccountNumber() + TAB
                + "account_name=" + AccountName() + TAB
                + "terminal_path=" + TerminalPath() + TAB
                + "platform_type=" + getPlatformType() + TAB
                + "sync_copy_manual_entry=" + SyncCopyManualEntry + TAB
                + "ea_executable_file=" + ea_executable_file + TAB
                + accountInfoPacket();

        sendTradeData(data);
        IsIntroRequired = false;

    }

    /*
void onChartEvent(const int id,         // Event ID 
                  const long& lparam,   // Parameter of type long event 
                  const double& dparam, // Parameter of type double event 
                  const String& sparam  // Parameter of type String events 
  ){

   if(id == CHARTEVENT_OBJECT_CLICK){
      
      if(StringFind(sparam, "lstPeerTicketsView"+"Item") == 0){
         int len = StringLen("lstPeerTicketsView"+"Item");
         int selecteItemIndex = (int)StringToInteger(StringSubstr(sparam, len));
         lstPeerTicketsView.Select(selecteItemIndex);
         
         updatePeerStoplossLabelsUI(lstPeerTicketsView.Select());
         
      }
      
   }

   
}
     */
    void updatePeerStoplossLabelsUI(String selectedPeerTicket) {

        int symb_digit = SymbolInfoInteger(Symbol(), SYMBOL_DIGITS);

        for (int i = 0; i < vSyncList.size(); i++) {

            VirtualSync vSync = (VirtualSync) vSyncList.get(i);

            if ((vSync.peer_ticket + "").equals(selectedPeerTicket)) {

                SwingUtilities.invokeLater(() -> 
                    lblPeerStoploss.setText(DoubleToString(NormalizeDouble(vSync.peer_stoploss, symb_digit)))
                );
                
                double clearance = exitClearance(vSync);

                int cpercent = (int) (EXIT_CLEARANCE_FACTOR * 100);
                int spread = (int) (vSync.peer_spread_point / getUsableSymbolPoint(OrderSymbol()));
                String str_less_peer_spread = "( Less " + EXIT_CLEARANCE_FACTOR * 100
                        + "% peer spread of " + spread + " )";

                if (WorkingPosition == OP_BUY) {
                    SwingUtilities.invokeLater(() -> {
                        lblActualExit.setText(DoubleToString(NormalizeDouble(vSync.peer_stoploss - clearance, symb_digit)));
                        labelActualExit.setText("Exit at Bid >=");
                    });

                } else if (WorkingPosition == OP_SELL) {
                    SwingUtilities.invokeLater(() -> {
                        lblActualExit.setText(DoubleToString(NormalizeDouble(vSync.peer_stoploss + clearance, symb_digit)));
                        labelActualExit.setText("Exit at Ask <=");
                    });
                }

                SwingUtilities.invokeLater(() -> labelLessPeerSpread.setText(str_less_peer_spread));

                break;
            }

        }
    }

    void creatGUI() {

        //dialog.Create(0, "DlgSyncTradeClient", 0, 500, 150, 0, 0);
        dialog.setSize(850, 500);
        dialog.setTitle("SyncTradeClient");       
        dialog.setName("DlgSyncTradeClient");
        dialog.setSize(850, 500);
        dialog.setLocation(400, 300);//come back
        dialog.setTitle("SyncTradeClient");
        

        //panelTop.Create(0, "panelTop", 0, 10, 10, 0, 0);
        panelTop.setSize(dialog.getWidth() - 30, 170);
        //panelTop.ColorBackground(clrWhiteSmoke);
        //panelTop.BorderType(BORDER_RAISED);      
        

        //panelCenter.Create(0, "panelCenter", 0, 10, 190, 0, 0);
        panelCenter.setSize(dialog.getWidth() - 30, 170);
        //panelCenter.ColorBackground(clrWhiteSmoke);
        //panelCenter.BorderType(BORDER_RAISED);      
        

        
        //panelBottom.Create(0, "panelBottom", 0, 10, 370, 0, 0);
        panelBottom.setSize(dialog.getWidth() - 30, 100);
        //panelBottom.ColorBackground(clrWhiteSmoke);
        //panelBottom.BorderType(BORDER_RAISED);


        
        //labelBalance.Create(0, "labelBalance", 0, dialog.getWidth() - 490, 20, 0, 0);
        labelBalance.setSize(200, 30);
        labelBalance.setText("Account Balance: ");

        
        //lblBalance.Create(0, "lblBalance", 0, dialog.getWidth() - 260, 20, 0, 0);
        lblBalance.setSize(200, 30);
        lblBalance.setText(DoubleToString(NormalizeDouble((float) AccountBalance(), 2), 2) + " " + AccountCurrency());
        
        
                
        //labelExpectedProfitRange.Create(0, "labelExpectedProfitRange", 0, 40, 80, 0, 0);
        labelExpectedProfitRange.setSize(150, 30);
        labelExpectedProfitRange.setText("Profit Range: ");


        
        
        //lblExpectedProfitRange.Create(0, "lblExpectedProfitRange", 0, 250, 80, 0, 0);
        lblExpectedProfitRange.setSize(250, 30);
        //lblExpectedProfitRange.FontSize(12);
        lblExpectedProfitRange.setForeground(Color.BLUE);
        lblExpectedProfitRange.setText(NormalizeDouble(ExpectedExitProfit, 2)
                + " " + AccountCurrency()
                + " to " + NormalizeDouble(ExpectedTargetProfit, 2)
                + " " + AccountCurrency());


        

        //labelExpectedBalanceRange.Create(0, "labelExpectedBalanceRange", 0, 40, 130, 0, 0);
        labelExpectedBalanceRange.setSize(150, 30);
        labelExpectedBalanceRange.setText("Balance Range: ");


        
        //lblExpectedBalanceRange.Create(0, "lblExpectedBalanceRange", 0, 250, 130, 0, 0);
        lblExpectedBalanceRange.setSize(250, 30);
        //lblExpectedBalanceRange.FontSize(12);
        lblExpectedBalanceRange.setForeground(Color.GREEN);
        lblExpectedBalanceRange.setText(NormalizeDouble(ExpectedExitBalance, 2)
                + " " + AccountCurrency()
                + " to " + NormalizeDouble(ExpectedTargetBalance, 2)
                + " " + AccountCurrency());
        
        

        //lstPeerTicketsView.Create(0, "lstPeerTicketsView", 0, 40, 250, 0, 300);
        lstPeerTicketsView.setSize(150, 100);
        //lstPeerTicketsView.VScrolled(true);
        //lstPeerTicketsView.BorderType(BORDER_SUNKEN);
        //lstPeerTicketsView.ColorBackground(clrWheat);

        //labelExitDescription.Create(0, "labelExitDescription", 0, 40, 200, 0, 0);
        labelExitDescription.setSize(800, 30);
        //labelExitDescription.Font("");
        labelExitDescription.setText("Attempts to exit trade as price nears or hits peer stoploss");

        //lblSymbol.Create(0, "lblSymbol", 0, 240, 250, 0, 0);
        lblSymbol.setSize(120, 30);
        lblSymbol.setText(Symbol());

        //lblPeerStoploss.Create(0, "lblPeerStoploss", 0, 400, 250, 0, 0);
        lblPeerStoploss.setSize(250, 40);
        //lblPeerStoploss.FontSize(16);
        //lblPeerStoploss.setForeground(clrBrown);
        //lblPeerStoploss.setText(1874.3);

        //labelActualExit.Create(0, "labelActualExit", 0, 220, 320, 0, 0);
        labelActualExit.setSize(120, 130);

        //labelActualExit.setText("Exit at >="); 
        //lblActualExit.Create(0, "lblActualExit", 0, 400, 320, 0, 0);
        lblActualExit.setSize(250, 40);
        //lblActualExit.FontSize(10);
        lblActualExit.setForeground(Color.RED);
        //lblActualExit.setText(1854.3);

        /*labelLessPeerSpread.Create(0, "labelLessPeerSpread", 0, 520, 325, 0, 0);
        labelLessPeerSpread.setSize(250, 40);
        labelLessPeerSpread.FontSize(8);
        //labelLessPeerSpread.setText("( Less 100% peer spread of 200 )");*/
        labelLessPeerSpread.setSize(250, 40);
        //labelLessPeerSpread.FontSize(8);
        
        
        /*lblAlert.Create(0, "lblAlert", 0, 40, 400, 0, 0);
        lblAlert.setSize(800, 30);
        //lblAlert.Font("");
        //lblAlert.setText("Alert Message");  */   
         lblAlert.setSize(800, 30);

        dialog.add(panelTop);
        dialog.add(panelCenter);
        dialog.add(panelBottom);
        dialog.add(labelBalance);
        dialog.add(lblBalance);
        dialog.add(labelExpectedProfitRange);
        dialog.add(lblExpectedProfitRange);
        dialog.add(labelExpectedBalanceRange);
        dialog.add(lblExpectedBalanceRange);
        dialog.add(lblPeerStoploss);
        dialog.add(lblSymbol);
        dialog.add(labelExitDescription);
        dialog.add(lstPeerTicketsView);
        dialog.add(lblActualExit);
        dialog.add(labelActualExit);
        dialog.add(labelLessPeerSpread);
        dialog.add(lblAlert);

        dialog.setVisible(true);

    }

    String getPlatformType() {
        /*
        int len = StringLen(__FILE__());
        String ext = StringSubstr(__FILE__(), len - 3);
        if ("mq4".equals(ext)) {
            return "mt4";
        } else if ("mq5".equals(ext)) {
            return "mt5";
        }*/

        return "Java";
    }

    void RunTimerIfNot() {
        if (!IsTimerRunning) {
            if (EventSetMillisecondTimer(RUN_INTERVAL)) {
                IsTimerRunning = true;
                Print("Timer set succesfully...");
            }
        }
    }

    void initControlVariables() {

        CumStoploss = 0;
        CumTarget = 0;
        CumSwap = 0;
        BuyCount = 0;
        BuyLimitCount = 0;
        BuyStopCount = 0;
        SellCount = 0;
        SellLimitCount = 0;
        SellStopCount = 0;
        HistoryTotal = 0;
        UnusedRecv = "";
        clearTicketsOfSyncCopy();
        clearTicketsOfSyncClose();
        clearTicketsOfSyncModify();
        clearTicketsOfPlacementOrder();
        PrintEAIsStopped = true;
        IsInitialSpreadFound = false;
        InitialSpreadTickCount = 0;
        ExitSpreadPoint = 0;
        PreSpreadPoint = 0;
        ExitSpreadLastTime = 0;
        NtTradeCount = 0;
        SpreadPointSum = 0;
        SpreadTickCount = 0;
        SpreadPoint = 0;
        LastAutoModifiedTarget = 0;
        PeerRealSymbolDigits = 0;
    }

//+------------------------------------------------------------------+
//| Expert deinitialization function                                 |
//+------------------------------------------------------------------+
    @Override
    public void OnDeinit(int reason) {

        //Close the communication channel 
        stc.CloseSocket();

        //destroy timer
        EventKillTimer();

        //delete vSyncList;      //NOT NEED THIS IS JAVA
        SwingUtilities.invokeLater(() -> 
            lstPeerTicketsView.removeAll() // come back
        );
        dialog.dispose();

        if (reason > 1) {
            ExpertRemove();//Prevent Reinitialization of this EA
            String attentMsg = "ATTENTION!!! SyncTradeClient has been removed";

            switch (reason) {
                case REASON_CHARTCHANGE:
                    attentMsg += " because the symbol or chart has changed";
                    break;
                case REASON_RECOMPILE:
                    attentMsg += " because the EA has been recompiled";
                    break;
                case REASON_CHARTCLOSE:
                    attentMsg += " because the chart has closed";
                    break;
                case REASON_PARAMETERS:
                    attentMsg += " because the input parameters was changed by the user";
                    break;
                case REASON_ACCOUNT:
                    attentMsg += " because another account has been activated or reconnection to the trade server has occurred due to changes in the account settings";
                    break;
                case REASON_TEMPLATE:
                    attentMsg += " because a new template has been applied";
                    break;
                case REASON_INITFAILED:
                    attentMsg += " because the EA failed to initialize";
                    break;
                case REASON_CLOSE:
                    attentMsg += " because the Terminal has been closed";
                    break;
            }
            Alert(attentMsg);
        }

    }
//+------------------------------------------------------------------+
//| Expert tick function                                             |
//+------------------------------------------------------------------+

    @Override
    public void OnTick() {
//---

        if (IsMarketClosed) {
            IsMarketJustOpen = true;
        }

        IsMarketClosed = false;

        RunTimerIfNot();

        isConnectionOpen = stc.IsSocketConnected();

        if (isConnectionOpen == false) {
            isWillRestartTerminalSent = false;
            isPrintAboutToRestart = false;
            return;
        }

        computeStoploss();

        checkPeerStoplossHit();

        sendAccountInfo();

        doRun();

        IsMarketJustOpen = false;
    }
//+------------------------------------------------------------------+
//| Timer function                                                   |
//+------------------------------------------------------------------+

    @Override
    public void OnTimer() {
        doRun();
    }

    void Terminate() {
        Terminate(null);
    }

    void Terminate(String msg) {
        if (IsTerminated) {
            return;
        }

        stc.CloseSocket();

        if (msg != null) {
            PlaySound("alert.wav");
            MessageBox(msg);
        }

        ExpertRemove();
        IsTerminated = true;
    }

    /*
datetime simulate_time = TimeLocal();

boolean SimulateIsConnected()
{
   

  long elapse = (ulong)(TimeLocal() - simulate_time);
   
      //Print(elapse);
      
      if(elapse > 2 * 60){      
         return false;
      }
      
      if(elapse > 1 * 60 && elapse < 1 * 60 + 3){
         Print("Connection Lost");
      }

   return true;
}
     */
    void resartTerminalOnConnectionLost() {

        if (!IsConnected()) {
            if (lastConnectionAvailsbleTime == 0) {
                return;
            }

            long elapse = (long) (TimeLocal() - lastConnectionAvailsbleTime);

            long max_time = MAX_ALLOW_TERMINAL_DISCONNECTED_MINUTE * 60;

            long half_time = max_time / 2;

            if (!isPrintAboutToRestart
                    && max_time > 10
                    && elapse > half_time) {
                String msg = StringFormat("Connection lost detected! Possibly terminal Is Outdated. Will restart terminal in about %d seconds if the connection is not restored.", half_time);
                Print(msg);
                Alert(msg);
                isPrintAboutToRestart = true;
            }

            if (!isWillRestartTerminalSent && elapse > max_time) {
                String data = "will_restart_due_to_connection_lost=" + TerminalPath() + TAB;
                sendData(data);
                isWillRestartTerminalSent = true;
            }

        } else {
            lastConnectionAvailsbleTime = TimeLocal();
        }

    }

    void doRun() {

        resartTerminalOnConnectionLost();

        if (OrdersTotal() == 0) {
            //initControlVariables(); //bug
        }

        double startTime = GetTickCount();

        if (Terminating) {
            Terminate();
            return;
        }

        lastKnownServerTime = TimeCurrent();

        if (TimeCurrent() > lastKnownServerTime) {
            IsMarketClosed = false;
        }

        int error_code = GetLastError();

        if (lastErrorCode != error_code) {
            lastErrorCode = error_code;
            if (lastErrorCode == 132) {

                IsMarketClosed = true;
            }
        }

        if (!channelIfNot()) {
            return;
        }

        validateConnection();

        if (IsIntroRequired) {
            if (StringLen(AccountCompany()) > 0) {
                sendIntro();
            }
        }

        sendPlaceOrderData();

        handleAccountBalanceChanged();

        ChangeStats stats = new ChangeStats();

        if (isOrderTradeStatusChange(stats)) {
            if (stats.TradeCountChanged) {
                trimVirtualSyncList();
                TradeAnalyze();
                sendTradeData("");
                sendDataAttrForSyncStateID();
            }

            /*if (stats.TradeCountIncreased) {
                sendPeerTakeProfitParam();
            }*/
            if (stats.TradeModified) {
                TradeAnalyze();

                restoreTarget();//new

                //sendTradeModifyData();  @Deprecated and removed 
            }

            if (stats.TradeSwapChanged) {
                sendPeerTakeProfitParam();
            }

        }

        handleNotifications();

        handleReceived(receiveData());

        //--- Get the spent time in milliseconds
        int elapse = (int) (GetTickCount() - (long) startTime);

        //Print("Benchmark: elapse = ", elapse, " milliseconds");
    }

    boolean channelIfNot() {

        if (stc.IsSocketConnected() == false) {

            if (!openConnection()) {
                return false;
            }

            startUpEA(null);

            if (isConnectionOpen) {                
                SwingUtilities.invokeLater(() -> 
                    lblAlert.setText(strRuning)
                );
            }
        }

        return true;
    }

    void handleNotifications() {

        int last_trade_count = NtTradeCount;
        NtTradeCount = OrdersTotal();

        if (NtTradeCount == 0 && last_trade_count > 0) {
            SendNotification(StringFormat("TRADE CLOSED\nBal: %s %s", DoubleToStr(AccountBalance(), 2), AccountCurrency()));

        }

    }

    void sendUnpairedNotification(TradePacket trade) {
        SendNotification(StringFormat("UNPAIRED DETECTED - Peer [%s, %s]", trade.peer_broker, trade.peer_account_number));
    }

    void sendEADisconnectionNotification() {
        SendNotification("ATTENTION!!! EA Disconnected from pipe server. Waiting...");
    }

    void reestablishedPairingNotification() {
        SendNotification("PAIRING RE-ESTABLISHED SUCCESSFULLY AFTER TERMINAL RESTART");
    }

    void restartedTerminalNotification() {
        SendNotification("TERMINAL HAS BEEN RESTARTED AND RESTORED BACK ONLINE AFTER OFFLINE DETECTION");
    }

    void peerTerminalToRestartNotification(TradePacket trade) {
        SendNotification(StringFormat("PEER OFFLINE DETECTED - Peer [%s, %s]\n\n Peer terminal will restart", trade.peer_broker, trade.peer_account_number));
    }

    void reportPeerTerminalToRestartFailed(TradePacket trade) {
        String report = StringFormat("FAILED - ATTENTION NEEDED: Could not restart peer terminal after offline detection: Peer [%s, %s]", trade.peer_broker, trade.peer_account_number);
        SendNotification(report);
        Alert(report);
        Print(report);
    }

//------------------------------------------------------
//Remove virtual sync objects representing closed trades
//------------------------------------------------------
    void trimVirtualSyncList() {

        for (int i = 0; i < vSyncList.size(); i++) {

            VirtualSync vSync = (VirtualSync) vSyncList.get(i);
            if (OrderSelect((int) vSync.own_ticket, SELECT_BY_TICKET)) {

                if (OrderCloseTime() == 0) {
                    continue; // skip since we only need closed orders
                }

                //At this point the order is closed
                //Print("for i = ", i);
                //Print("About to deleted vSync.own_ticket ", vSync.own_ticket);
                //Print("Before deleted vSyncList.Total() ", vSyncList.Total());
                vSyncList.remove(i); //delete from sync list

                
                //lstPeerTicketsView.remove(i); //alse delete from GUI list view
                PeerTicketModel model = lstPeerTicketsView.getModel();
                model.removeAt(i);
                i--;

                //Print("Deleted vSync.own_ticket", vSync.own_ticket);
                //Print("After deleted vSyncList.Total() ", vSyncList.Total());
                continue;
            }

        }

    }

    void TradeAnalyze() {

        int total = OrdersTotal();

        AccountSwapCost = 0;
        AccountCommissionCost = 0;
        AccountSwapPerDay = 0;
        AccountTradeCost = 0;
        ExpectedHedgeProfit = 0;
        for (int i = 0; i < total; i++) {

            if (OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {

                AccountTradeCost += OrderCommission() + OrderSwap();

                if (OrderType() == OP_BUY) {
                    AccountSwapPerDay += MarketInfo(OrderSymbol(), MODE_SWAPLONG);
                }

                if (OrderType() == OP_SELL) {
                    AccountSwapPerDay += MarketInfo(OrderSymbol(), MODE_SWAPSHORT);
                }

                AccountSwapCost += OrderSwap();
                AccountCommissionCost += OrderCommission();

                //calculate entry spread - I AM ASSUMING THIS CALCULATION IS CORRECT - come back to confirm correctness
                /*
                  // IT HAS BEEN CONFIRMED THAT SPREAD COST CANNOT BE DETERMINED THIS WAY
                  double pip_move = MathAbs(OrderOpenPrice() - OrderClosePrice())/getUsableSymbolPoint(OrderSymbol()); 
                  double profit = pip_move * OrderLots() * symbolTickValue(OrderSymbol()) * 10;
                  double entry_spread_cost = profit - (OrderProfit() - OrderCommission() - OrderSwap());
                 */
                //UNCOMMENT THE LINE BELOW IF THE ABOVE CALCULATION IS NOT CORRECT. 
                //LETS MANAGE THE FAIRLY ACCURATE ONE BELOW WHICH DOES ONLY GIVE 
                //THE CURRENT MARKET SPREAD OF THE SYMBOL AND NOT ITS ENTRY SPREAD
                double current_spread_pips = MathAbs(MarketInfo(OrderSymbol(), MODE_ASK) - MarketInfo(OrderSymbol(), MODE_BID)) / getUsableSymbolPoint(OrderSymbol());
                double entry_spread_cost = current_spread_pips * OrderLots() * MarketInfo(OrderSymbol(), MODE_TICKVALUE);

                AccountTradeCost -= entry_spread_cost;

                double pip_win = MathAbs(OrderOpenPrice() - OrderTakeProfit()) / getUsableSymbolPoint(OrderSymbol());
                double target_profit = pip_win * OrderLots() * symbolTickValue(OrderSymbol());
                ExpectedHedgeProfit = target_profit;

            }

        }
    }

    String accountInfoPacket() {

        return "account_balance=" + AccountBalance() + TAB
                + "account_equity=" + AccountEquity() + TAB
                + "account_credit=" + AccountCredit() + TAB
                + "account_currency=" + AccountCurrency() + TAB
                + "account_leverage=" + AccountLeverage() + TAB
                + "account_margin=" + AccountMargin() + TAB
                + "account_stopout_level=" + AccountStopoutLevel() + TAB
                + "account_profit=" + AccountProfit() + TAB
                + "account_free_margin=" + AccountFreeMargin() + TAB
                + "account_swap_per_day=" + AccountSwapPerDay + TAB
                + "account_swap_cost=" + AccountSwapCost + TAB
                + "account_commission_cost=" + AccountCommissionCost + TAB
                + "account_trade_cost=" + AccountTradeCost + TAB
                + "total_open_orders=" + OrdersTotal() + TAB
                + "chart_symbol=" + Symbol() + TAB
                + "chart_symbol_digits=" + SymbolInfoInteger(Symbol(), SYMBOL_DIGITS) + TAB
                + "chart_symbol_max_lot_size=" + SymbolInfoDouble(Symbol(), SYMBOL_VOLUME_MAX) + TAB
                + "chart_symbol_min_lot_size=" + SymbolInfoDouble(Symbol(), SYMBOL_VOLUME_MIN) + TAB
                + "chart_symbol_tick_value=" + symbolTickValue(Symbol()) + TAB
                + "chart_symbol_tick_size=" + SymbolInfoDouble(Symbol(), SYMBOL_TRADE_TICK_SIZE) + TAB
                + "chart_symbol_swap_long=" + SymbolInfoDouble(Symbol(), SYMBOL_SWAP_LONG) + TAB
                + "chart_symbol_swap_short=" + SymbolInfoDouble(Symbol(), SYMBOL_SWAP_SHORT) + TAB
                + "chart_symbol_spread=" + SymbolInfoInteger(Symbol(), SYMBOL_SPREAD) + TAB
                + "chart_symbol_trade_units=" + SymbolInfoDouble(Symbol(), SYMBOL_TRADE_CONTRACT_SIZE) + TAB
                + "chart_market_price=" + Close(0) + TAB
                + "exchange_rate_for_margin_requirement=" + iClose(SymbolForMarginReqirement, 0, 0) + TAB
                + "expected_exit_profit=" + ExpectedExitProfit + TAB
                + "expected_target_profit=" + ExpectedTargetProfit + TAB
                + "expected_exit_balance=" + ExpectedExitBalance + TAB
                + "expected_target_balance=" + ExpectedTargetBalance + TAB
                + "terminal_connected=" + IsConnected() + TAB
                + "only_trade_with_credit=" + (OnlyTradeWithCredit ? "true" : "false") + TAB
                + "chart_symbol_trade_allowed=" + (MarketInfo(Symbol(), MODE_TRADEALLOWED) > 0 ? "true" : "false") + TAB
                + "sync_state_pair_id=" + SyncStatePairID + TAB;

    }

    void sendAccountInfo() {
        sendData(accountInfoPacket());
    }

    void sendPeerTakeProfitParam() {

        AccountInfoUsedByPeer();

        String data = "account_margin=" + AccountMargin() + TAB
                + "stopout_level=" + AccountStopoutLevel() + TAB
                + "account_balance=" + AccountBalance() + TAB
                + "account_credit=" + AccountCredit() + TAB
                + "total_commission=" + TotalCommission + TAB
                + "total_swap=" + TotalSwap + TAB
                + "total_lot_size=" + TotalLotSize + TAB
                + "total_open_orders=" + OrdersTotal() + TAB
                + "contract_size=" + MarketInfo(Symbol(), MODE_LOTSIZE) + TAB
                + "position=" + Position + TAB
                + "base_open_price=" + baseOpenPrice() + TAB
                + "peer_take_profit_param=true" + TAB;

        sendData(data);
    }

    void handleAccountBalanceChanged() {

        if (MyAccountBalance == AccountBalance()) {
            return;
        }

        MyAccountBalance = AccountBalance();

        String data = "account_balance=" + MyAccountBalance + TAB
                + "account_balance_changed=true" + TAB;

        sendData(data);

        SwingUtilities.invokeLater(() -> 
            lblBalance.setText(DoubleToString(NormalizeDouble((float) AccountBalance(), 2), 2) + " " + AccountCurrency())
        );
    }

    double getUsableSymbolPoint(String symbol) {

        double _point = MarketInfo(symbol, MODE_TICKSIZE) / MarketInfo(symbol, MODE_TICKVALUE);

        return _point;
    }

    void handleReceived(String recv) {

        recv = UnusedRecv + recv;

        if ("".equals(recv)) {
            return;
        }

        int new_line_end = -1;
        int recv_len = StringLen(recv);
        for (int i = recv_len - 1; i > -1; i--) {
            if (StringGetChar(recv, i) == StringGetChar(NEW_LINE, 0)) {
                new_line_end = i;
                break;
            }

        }

        if (new_line_end > -1) {
            String r = StringSubstr(recv, 0, new_line_end);

            String arr[] = {};
            int count = StringSplit(r, StringGetChar(NEW_LINE, 0), arr);

            for (int i = 0; i < count; i++) {
                receivedLine(arr[i]);

            }

            int pos = new_line_end + 1;
            if (StringLen(recv) >= pos + 1) {
                UnusedRecv = StringSubstr(recv, pos, recv_len - pos);
            } else {
                UnusedRecv = "";
            }

        } else {
            UnusedRecv = recv;

        }

    }

    String getCorrespondingSymbol(String symbol_group) {

        String symb_arr[] = {};

        int len = StringSplit(symbol_group, ';', symb_arr);

        for (int i = 0; i < len; i++) {
            String symb = symb_arr[i];

            double try_ask = MarketInfo(symb, MODE_ASK);
            if (GetLastError() == ERR_UNKNOWN_SYMBOL) {
                ResetLastError();
                continue;
            }

            if (try_ask > 0) {
                return symb;
            }

        }

        return "SOMETHING_IS_WORONG_HERE";//at this point something must be wrong
    }

    void duplicateEA() {

        String err = "Duplicate EA Not Allowed! EA on chart " + Symbol() + " has been removed because it was found to be a duplicate.";
        Alert(err);
        Print(err);
        Terminating = true;

    }

    void reloadEAOngoingInstallation(TradePacket trade_packet_struct) {

        if ("true".equals(trade_packet_struct.immediate)) {
            String msg = "Please Reload EA. Due to ongoing installations the EA has been forcibly removed.";
            Alert(msg);
            Print(msg);
            Terminating = true;
        }

    }

    void receivedLine(String line) {

        TradePacket trade_packet_struct = new TradePacket();

        toReceivedTradePacket(line, trade_packet_struct);

        //command
        if ("check_enough_money".equals(trade_packet_struct.command)) {
            sendCommandCheckEnoughMoney(trade_packet_struct);
        }

        if ("check_tradable".equals(trade_packet_struct.command)) {
            sendCommandCheckTradable(trade_packet_struct);
        }

        if ("duplicate_ea".equals(trade_packet_struct.command)) {
            duplicateEA();
        }

        if ("shutdown_terminal_for_restart".equals(trade_packet_struct.command)) {
            //immediately close the terminal - the gui app will restart it thereafter
            TerminalClose(0);
        }

        if ("re_established_pairing".equals(trade_packet_struct.command)) {
            reestablishedPairingNotification();
        }

        if ("re_started_terminal".equals(trade_packet_struct.command)) {
            restartedTerminalNotification();
        }

        if ("peer_terminal_to_restart".equals(trade_packet_struct.command)) {
            peerTerminalToRestartNotification(trade_packet_struct);
        }

        if ("report_peer_terminal_to_restart_failed".equals(trade_packet_struct.command)) {
            reportPeerTerminalToRestartFailed(trade_packet_struct);
        }

        if ("reload_ea_ongoing_installation".equals(trade_packet_struct.command)) {
            reloadEAOngoingInstallation(trade_packet_struct);
        }

        if ("virtual_sync".equals(trade_packet_struct.command)) {
            setVirtualSync(trade_packet_struct);
        }

        //action
        if ("intro".equals(trade_packet_struct.action)) {
            IsIntroRequired = true; //force the EA to send the intro
        }

        if ("sync_place_order".equals(trade_packet_struct.action)) {
            sendPacketTrade(trade_packet_struct);
        }

        if ("request_take_profit_param".equals(trade_packet_struct.action)) {
            sendPeerTakeProfitParam();
        }

        if ("set_take_profit".equals(trade_packet_struct.action)) {
            computeTakeProfit();
        }

        if ("sync_copy".equals(trade_packet_struct.action)) {
            sendPacketTrade(trade_packet_struct);
        }

        if ("sync_close".equals(trade_packet_struct.action)) {
            sendPacketClose(trade_packet_struct);
        }

        if ("sync_partial_close".equals(trade_packet_struct.action)) {
            sendPacketClose(trade_packet_struct);
        }

        if ("own_close".equals(trade_packet_struct.action)) {
            sendPacketClose(trade_packet_struct);
        }

        if ("sync_modify_target".equals(trade_packet_struct.action)) {
            sendPacketSyncModifyTarget(trade_packet_struct);
        }

        if ("unpaired_notification".equals(trade_packet_struct.action)) {
            sendUnpairedNotification(trade_packet_struct);
        }

        if ("sync_state_paird_id".equals(trade_packet_struct.action)) {
            SyncStatePairID = trade_packet_struct.sync_state_paird_id;

            //Print("SyncStatePairID =",SyncStatePairID);// TESTING!!!
        }

    }
//--------------------------------------------------
// initilize the trade packet struct otherwise the garbage values will be assign to it
//--------------------------------------------------

    void initTradeStrct(TradePacket trade_packet_struct) {
        trade_packet_struct.command = "";
        trade_packet_struct.command_id = "";
        trade_packet_struct.action = "";
        trade_packet_struct.force = "";
        trade_packet_struct.uuid = "";
        trade_packet_struct.origin_ticket = "-1";

        trade_packet_struct.immediate = "false";

        trade_packet_struct.signal_time = 0;
        trade_packet_struct.close_time = 0;
        trade_packet_struct.copy_type = "";
        trade_packet_struct.lot_size = 0;
        trade_packet_struct.open_price = 0;
        trade_packet_struct.open_time = 0;
        trade_packet_struct.position = "";
        trade_packet_struct.stoploss = 0;
        trade_packet_struct.symbol = "";
        trade_packet_struct.target = 0;
        trade_packet_struct.ticket = -1;
        trade_packet_struct.account_balance = 0;
        trade_packet_struct.floating_balance = 0;

        trade_packet_struct.own_ticket = 0;
        trade_packet_struct.peer_ticket = 0;
        trade_packet_struct.peer_stoploss = 0;
        trade_packet_struct.peer_spread_point = 0;
        trade_packet_struct.partial_closed_lot_fraction = 0;
    }

    void toReceivedTradePacket(String line, TradePacket trade_packet_struct) {

        if (!line.equals(PING_PACKET)) {
            Print("RECEIVED: ", line);//TESTING!!!
        }

        initTradeStrct(trade_packet_struct);

        String token[] = {};
        int size = StringSplit(line, StringGetChar(TAB, 0), token);

        for (int i = 0; i < size; i++) {
            String param[] = {};
            StringSplit(token[i], '=', param);
            String name = param[0];
            String value = param[1];

            trade_packet_struct.signal_time = (long) TimeCurrent();

            if ("command".equals(name)) {
                trade_packet_struct.command = value;
            }

            if ("command_id".equals(name)) {
                trade_packet_struct.command_id = value;
            }

            if ("action".equals(name)) {
                trade_packet_struct.action = value;
            }

            if ("uuid".equals(name)) {
                trade_packet_struct.uuid = value;
            }

            if ("immediate".equals(name)) {
                trade_packet_struct.immediate = Boolean.toString("true".equals(value));
            }

            if ("force".equals(name)) {
                trade_packet_struct.force = value;
            }

            if ("reason".equals(name)) {
                trade_packet_struct.reason = value;
            }

            if ("symbol".equals(name)) {
                trade_packet_struct.symbol = defactorSymbol(value);
            }

            /*//deprecated
         if(name == "symbol_group" && StringLen(value) > 0)
         { 
            trade_packet_struct.symbol = getCorrespondingSymbol(value);
         }
             */
            if ("relative_symbol".equals(name) && StringLen(value) > 0)// yes relative_symbol must come below symbol so that if known we use it straightway 
            {
                trade_packet_struct.symbol = value;
            }

            if ("ticket".equals(name)) {
                trade_packet_struct.ticket = StringToInteger(value);
            }

            if ("origin_ticket".equals(name)) {
                trade_packet_struct.origin_ticket = value;
            }

            if ("position".equals(name)) {
                trade_packet_struct.position = value;
            }

            if ("lot_size".equals(name)) {
                trade_packet_struct.lot_size = StringToDouble(value);
            }

            if ("open_price".equals(name)) {
                trade_packet_struct.open_price = StringToDouble(value);
            }

            if ("trade_copy_type".equals(name)) {
                trade_packet_struct.copy_type = value;
            }

            if ("target".equals(name)) {
                trade_packet_struct.target = StringToDouble(value);
            }

            if ("stoploss".equals(name)) {
                trade_packet_struct.stoploss = StringToDouble(value);
            }

            if ("spread_point".equals(name)) {
                trade_packet_struct.spread_point = StringToDouble(value);
            }

            if ("peer_broker".equals(name)) {
                trade_packet_struct.peer_broker = value;
            }

            if ("peer_account_number".equals(name)) {
                trade_packet_struct.peer_account_number = value;
            }

            if ("own_ticket".equals(name)) {
                trade_packet_struct.own_ticket = StringToInteger(value);
            }

            if ("peer_ticket".equals(name)) {
                trade_packet_struct.peer_ticket = StringToInteger(value);
            }

            if ("peer_stoploss".equals(name)) {
                trade_packet_struct.peer_stoploss = StringToDouble(value);
            }

            if ("peer_spread_point".equals(name)) {
                trade_packet_struct.peer_spread_point = StringToDouble(value);
            }

            if ("sync_state_paird_id".equals(name)) {
                trade_packet_struct.sync_state_paird_id = value;
            }

            if ("partial_closed_lot_fraction".equals(name)) {
                trade_packet_struct.partial_closed_lot_fraction = StringToDouble(value);
            }

            if ("peer_symbol_digits".equals(name)) {
                PeerRealSymbolDigits = StringToInteger(value);

                Print("PeerRealSymbolDigits ", PeerRealSymbolDigits);
            }

            if ("peer_account_margin".equals(name)) {
                PeerAccountMargin = StringToDouble(value);
            }

            if ("peer_stopout_level".equals(name)) {
                PeerStopoutLevel = StringToDouble(value);
            }

            if ("peer_account_balance".equals(name)) {
                PeerAccountBalance = StringToDouble(value);
            }

            if ("peer_account_credit".equals(name)) {
                PeerAccountCredit = StringToDouble(value);
            }

            if ("peer_total_commission".equals(name)) {
                PeerTotalCommission = StringToDouble(value);
            }

            if ("peer_total_swap".equals(name)) {
                PeerTotalSwap = StringToDouble(value);
            }

            if ("peer_total_lot_size".equals(name)) {
                PeerTotalLotSize = StringToDouble(value);
            }

            if ("peer_contract_size".equals(name)) {
                PeerContractSize = StringToDouble(value);
            }

            if ("peer_position".equals(name)) {
                PeerPosition = "BUY".equals(value) ? OP_BUY
                        : "SELL".equals(value) ? OP_SELL
                        : -1;
            }

            if ("peer_base_open_price".equals(name)) {
                PeerBaseOpenPrice = StringToDouble(value);
            }

            if ("peer_safety_spread".equals(name)) {
                PeerSafetySpread = StringToDouble(value);
            }

            //EA JTrade Properties
            if ("sync_copy_manual_entry".equals(name)) {
                SyncCopyManualEntry = "true".equals(value) || "1".equals(value);

                Print("SyncCopyManualEntry ", SyncCopyManualEntry);
            }

            if ("exit_clearance_factor".equals(name)) {
                switch (StringToInteger(value)) {
                    case 0:
                        exitClearanceFactor = _0_PERCENT;
                        EXIT_CLEARANCE_FACTOR = 0;
                        break;
                    case 30:
                        exitClearanceFactor = _30_PERCENT;
                        EXIT_CLEARANCE_FACTOR = 0.3;
                        break;
                    case 50:
                        exitClearanceFactor = _50_PERCENT;
                        EXIT_CLEARANCE_FACTOR = 0.5;
                        break;
                    case 80:
                        exitClearanceFactor = _80_PERCENT;
                        EXIT_CLEARANCE_FACTOR = 0.8;
                        break;
                    case 100:
                        exitClearanceFactor = _100_PERCENT;
                        EXIT_CLEARANCE_FACTOR = 1;
                        break;
                }

                Print("EXIT_CLEARANCE_FACTOR ", EXIT_CLEARANCE_FACTOR);
            }

            if ("only_trade_with_credit".equals(name)) {
                OnlyTradeWithCredit = "true".equals(value) || "1".equals(value);

                Print("OnlyTradeWithCredit ", OnlyTradeWithCredit);
            }

            if ("enable_exit_at_peer_stoploss".equals(name)) {
                IsExitAtPeerStoplossEnabled = "true".equals(value) || "1".equals(value);

                Print("IsExitAtPeerStoplossEnabled ", IsExitAtPeerStoplossEnabled);
            }

        }

    }

    double GetRequiredMargin(String symbol, double lotSize) {
        // Get the leverage of the account
        long leverage = AccountInfoInteger(ACCOUNT_LEVERAGE);

        // Get the current price of the symbol
        double price = SymbolInfoDouble(symbol, SYMBOL_BID);

        // Get the contract size (standard is 100,000 units for forex)
        double contractSize = SymbolInfoDouble(symbol, SYMBOL_TRADE_CONTRACT_SIZE);

        // Calculate the margin required
        double margin = (lotSize * contractSize * price) / leverage;

        return margin;
    }

    void sendCommandCheckEnoughMoney(TradePacket trade) {

        double max_volume = SymbolInfoDouble(Symbol(), SYMBOL_VOLUME_MAX);

        double lot_size = trade.lot_size > max_volume ? max_volume : trade.lot_size;

        int order_type = toIntOrderType(trade.position);

        //check if money is enough
        double free_margin = AccountFreeMarginCheck(Symbol(), order_type, lot_size);
        double required_margin = AccountBalance() - free_margin;
        int last_error = GetLastError();

        //double check
        double manaul_computed_required_margin = GetRequiredMargin(Symbol(), lot_size);
        boolean manual_check_enough_money = AccountInfoDouble(ACCOUNT_BALANCE) > manaul_computed_required_margin;

        if (free_margin <= 0 || last_error == 134) {
            String error = "No enough money -  Free margin is zero or negative";
            if (last_error == 134) {
                error = ErrorDescription(last_error);//No enough money
                ResetLastError();
            }
            if (!manual_check_enough_money) {  //double check
                sendData(checkEnoughMoneyCommandResponse(false, trade, error));
                return;
            }
        }

        sendData(checkEnoughMoneyCommandResponse(true, trade, DoubleToString(required_margin)));

    }

    void sendCommandCheckTradable(TradePacket trade) {

        boolean connected = TerminalInfoInteger(TERMINAL_CONNECTED);
        if (!connected) {
            sendData(checkTradableCommandResponse(false, trade, "Terminal Disconnected!"));
            return;
        }

        boolean trade_allow = AccountInfoInteger(ACCOUNT_TRADE_ALLOWED) > 0;
        if (!trade_allow) {
            sendData(checkTradableCommandResponse(false, trade, "Trade not allowed!"));
            return;
        }

        boolean trade_expert = AccountInfoInteger(ACCOUNT_TRADE_EXPERT) > 0;
        if (!trade_expert) {
            sendData(checkTradableCommandResponse(false, trade, "Automated trading is forbidden for the account " + AccountInfoInteger(ACCOUNT_LOGIN)
                    + " at the trade server side"));
            return;
        }

        int symbol_trade_mode = SymbolInfoInteger(Symbol(), SYMBOL_TRADE_MODE);
        if (symbol_trade_mode == SYMBOL_TRADE_MODE_DISABLED) {
            sendData(checkTradableCommandResponse(false, trade, "Trade is disabled for the symbol - " + Symbol()));
            return;
        }

        sendData(checkTradableCommandResponse(true, trade, "success"));

    }

    void setVirtualSync(TradePacket trade) {

        boolean found = false;

        for (int i = 0; i < vSyncList.size(); i++) {
            VirtualSync vSync = (VirtualSync) vSyncList.get(i);
            if (vSync.own_ticket == trade.own_ticket && vSync.peer_ticket == trade.peer_ticket) {
                if (trade.peer_stoploss != 0) {

                    vSync.peer_stoploss = trade.peer_stoploss;

                    //update GUI list view
                    SwingUtilities.invokeLater(() -> 
                        lstPeerTicketsView.ItemUpdate(i, vSync.peer_ticket, vSync.peer_ticket)
                    );

                }
                if (trade.peer_spread_point != 0) {
                    vSync.peer_spread_point = trade.peer_spread_point;
                }

                found = true;
            }
        }

        if (!found) {
            VirtualSync vSync = new VirtualSync();
            vSync.own_ticket = trade.own_ticket;
            vSync.peer_ticket = trade.peer_ticket;
            vSync.peer_stoploss = trade.peer_stoploss;
            vSync.peer_spread_point = trade.peer_spread_point;

            vSyncList.add(vSync);

            //add item to GUI list view
            SwingUtilities.invokeLater(() -> {
                lstPeerTicketsView.AddItem(vSync.peer_ticket, vSync.peer_ticket);

                lstPeerTicketsView.setSelectedIndex(0); //select the first in the list
            });
            
            updatePeerStoplossLabelsUI((String) lstPeerTicketsView.getSelectedValue());
        }

        ExpectedExitProfit = 0; // profit if exit at peer stoploss
        ExpectedTargetProfit = 0;// profit if exit at main target
        ExpectedExitBalance = 0;// balance if exit at peer stoploss
        ExpectedTargetBalance = 0;// balance if exit at main target

        Print(" -----------------START VIRTUAL SYNC------------------------- ");

        for (int i = 0; i < vSyncList.size(); i++) {
            VirtualSync vSync = (VirtualSync) vSyncList.get(i);

            //Print("vSync.own_ticket ",vSync.own_ticket);
            //Print("vSync.peer_ticket ",vSync.peer_ticket);
            //Print("vSync.peer_stoploss ",vSync.peer_stoploss);
            //Print("vSync.peer_spread_point ",vSync.peer_spread_point);                 
            if (OrderSelect((int) vSync.own_ticket, SELECT_BY_TICKET) == true) {

                if (OrderCloseTime() > 0) {
                    continue;//skip since we only need open orders
                }

                //At this point the order is still open
                ExpectedExitProfit += OrderSwap() + OrderCommission() + OrderLots() * MathAbs(OrderOpenPrice() - vSync.peer_stoploss) / getUsableSymbolPoint(OrderSymbol());
                ExpectedTargetProfit += OrderSwap() + OrderCommission() + OrderLots() * MathAbs(OrderOpenPrice() - OrderTakeProfit()) / getUsableSymbolPoint(OrderSymbol());

                ExpectedExitBalance = AccountBalance() + ExpectedExitProfit;
                ExpectedTargetBalance = AccountBalance() + ExpectedTargetProfit;

            }
        }

        SwingUtilities.invokeLater(() -> {
            lblExpectedProfitRange.setText(NormalizeDouble(ExpectedExitProfit, 2)
                    + " " + AccountCurrency()
                    + " / " + NormalizeDouble(ExpectedTargetProfit, 2)
                    + " " + AccountCurrency());

            lblExpectedBalanceRange.setText(NormalizeDouble(ExpectedExitBalance, 2)
                    + " " + AccountCurrency()
                    + " / " + NormalizeDouble(ExpectedTargetBalance, 2)
                    + " " + AccountCurrency());
        });

        //Print("ExpectedExitProfit ",ExpectedExitProfit);
        //Print("ExpectedTargetProfit ",ExpectedTargetProfit);
        //Print("expected_exit_bal ",ExpectedExitBalance);
        //Print("expected_target_bal ",ExpectedTargetBalance);
        //Print(" -----------------END VIRTUAL SYNC------------------------- ");
    }

    void checkPeerStoplossHit() {

        if (!IsExitAtPeerStoplossEnabled) {
            return;
        }

        for (int n = 0; n < vSyncList.size(); n++) {

            VirtualSync vSync = (VirtualSync) vSyncList.get(n);

            if (vSync.peer_stoploss <= 0) {
                continue; //Skip since no stoploss yet.
            }

            if (OrderSelect((long) vSync.own_ticket, SELECT_BY_TICKET)) {
                if (OrderCloseTime() > 0) {
                    continue;//skip since we only need open orders
                }

                //At this point the order is still open
                if (OrderType() == OP_BUY) {
                    //Which means peer position is SELL
                    checkPeerStoplossHit0(OP_SELL, vSync);
                } else if (OrderType() == OP_SELL) {
                    //Which means peer position is BUY
                    checkPeerStoplossHit0(OP_BUY, vSync);
                }

            }
        }

    }

    double exitClearance(VirtualSync vSync) {
        return vSync.peer_spread_point * EXIT_CLEARANCE_FACTOR;
    }

//-------------------------------------
//This method is use to prevent us from missing
//the correct price to test our exit condition especially
//during high volatility where the EA is not fast enough 
//to see the current prices to test our conditon with.
//It can be dangerous if we missing price as per our condition.
//So the trick is to use the highest or lowest price within
//setting safe (reasonable) period to match our condition 
//
//Now suppose the account position is BUY and the peer account is SELL
//Then this account eixt condition will be that if the peer Ask price (ie this account current price named this way for logic clarity)
//is greater than or equal to the exit price (stoploss of peer minus clearance), then close the 
//the trade. But what if the patform hangs or a distrupting event happens (like too high volatility)
//at that period the condition was met and EA could to detect is first and the market reverses
//completely and never meets that condition  again, that will just mean blowing out the entire
//account on both sides. Now to prevent this posssibility we will test with the 
//highest high of last 3 one-minute bars instead of just the current price (we named as peer Ask price).
//The sense behind this is that this unexpected happening that prevented this account EA
//from seeing the exist condition first time may not last for more than 3 minute 
//thus allowing the condition to be detected even  though very late, 
//thanks to the High price stamped on all bars
//------------------------------------
    double safePriceToCompareWith(int peerPos, double exit_price, double peer_spread_point) {

        int LAST_BARS_COUNT = 3;

        debugPriceIsClosePrice = false;//for debug purpose

        //let know time elapse after trade open
        int timeElapseInsec = (int) (TimeCurrent() - OrderOpenTime());
        if (timeElapseInsec <= LAST_BARS_COUNT * 60) {
            debugPriceIsClosePrice = true;//for debug purpose
            return Close(0); //just return current prices within the first minute as it is unsafe to test with Low or High with this period
        }

        //At this point we are on the next N bars of the one minute timeframe since open trade. 
        //This is a safe (reasonable) period to use High or Low to test our condition
        int one_minute_bar_count = timeElapseInsec / 60;

        //we just need few bars like say 3 to reduce computation
        //Since we know the EA delay (slowness - time b/w execution)
        //can not be more than 3 minutes except is something is 
        //seriously wrong
        if (one_minute_bar_count > LAST_BARS_COUNT) {
            one_minute_bar_count = LAST_BARS_COUNT;
        }

        //At this point at least a new one minute bar is created
        double peak_price = 0;

        int shift = 0;
        if (peerPos == OP_BUY) {
            shift = iLowest(Symbol(), PERIOD_M1, MODE_LOW, one_minute_bar_count);

            if (shift == -1) {
                PrintFormat("Error in iLowest. Error code=%d", GetLastError());
            }

        } else {
            shift = iHighest(Symbol(), PERIOD_M1, MODE_HIGH, one_minute_bar_count);

            if (shift == -1) {
                PrintFormat("Error in iHighest. Error code=%d", GetLastError());
            }
        }

        if (shift == -1) {
            //An error occoure so just return the high or low of the current bar  
            shift = 0;
        }

        //ASSERTION 1 - Just checking if this is a bug - unexpected hitting the peer exit stoploss
        if (shift > LAST_BARS_COUNT) {
            Print("BUG PREVENTED!!!  POSSIBLY NONSENSE VALUE. shift = ", shift);
            shift = 0;
        }

        if (peerPos == OP_BUY) {
            peak_price = Low(shift);
        } else {
            peak_price = High(shift);
        }

        //Check if the peer stoploss intercepts the high / low with the last N bars on the current timeframe
        if (timeElapseInsec <= LAST_BARS_COUNT * Period() * 60) {

            if (peerPos == OP_BUY && peak_price <= exit_price) {
                bugResolved++;
                peak_price = Close(0);
            }

            if (peerPos == OP_SELL && peak_price >= peak_price + peer_spread_point) {
                bugResolved++;
                peak_price = Close(0);
            }

        }

        if (bugResolved == 1) {

            Alert("GREAT! MAJOR BUG IS RESOLVED");
            Print("GREAT! MAJOR BUG IS RESOLVED");

        }

        return peak_price;
    }

    void checkPeerStoplossHit0(int peerPos, VirtualSync vSync) {

        //Print("checkPeerStoplossHit0");
        boolean success = false;
        boolean attempted = false;

        //The purpose of this clearance is to ensure this account (POSITIVE SIDE) sees
        //this stoploss price before the peer account (NEGATIVE SIDE) to ensure the positive side
        //closes first before the negative account side.
        double clearance = exitClearance(vSync);

        double exit_price = peerPos == OP_BUY
                ? vSync.peer_stoploss + clearance
                : vSync.peer_stoploss - clearance;

        double safePrice = safePriceToCompareWith(peerPos, exit_price, vSync.peer_spread_point);//which is Highest High or Lowest Low of N bars - Please see Comments on safePriceToCompareWith() method for more explanation      

        //BUY enters at Ask price but closes at Bid price
        double PeerBid = safePrice; //Which is Close[0] but we are not using the Close[0] directly to handle missed price condition - Please see Comments on safePriceToCompareWith() method for more explanation

        //SELL enters at Bid price but closes at Ask price
        double PeerAsk = safePrice + vSync.peer_spread_point; //yes, we are calculating what should be the Ask price on the peer account. We know it is the Close[0] plus the spread - But we are not using the Close[0] directly to handle missed price condition - Please see Comments on safePriceToCompareWith() method for more explanation

        if (peerPos == OP_BUY) {

            boolean PrevIsHitPeerStoploss = vSync.IsHitPeerStoploss; // if true then it probably is a retry otherwise a big bug - this is while we are storing the value

            if (PeerBid <= exit_price || vSync.IsHitPeerStoploss) {
                vSync.IsHitPeerStoploss = true;
                attempted = true;
                //NOTE: SELL closes at Ask Price - since Peer is BUY then own is SELL and will close at Ask price
                int err_code = closeSelectedPosition(OrderLots(), MODE_ASK);
                success = err_code == 0;

                Print("HIT Peer BUY Stoploss exit_price ", exit_price,
                        " safePrice ", safePrice,
                        " debugPriceIsClosePrice ", debugPriceIsClosePrice, //whether the safePrice is the current price or high/low
                        " vSync.peer_stoploss ", vSync.peer_stoploss,
                        " vSync.peer_spread_point ", vSync.peer_spread_point,
                        " clearance ", clearance,
                        " PrevIsHitPeerStoploss ", PrevIsHitPeerStoploss);
            }

        }

        if (peerPos == OP_SELL) {

            boolean PrevIsHitPeerStoploss = vSync.IsHitPeerStoploss; // if true then it probably is a retry otherwise a big bug - this is while we are storing the value

            if (PeerAsk >= exit_price || vSync.IsHitPeerStoploss) {
                vSync.IsHitPeerStoploss = true;
                attempted = true;
                //NOTE: BUY closeS at Bid Price - since Peer is SELL then own is BUY and will close at Bid price
                int err_code = closeSelectedPosition(OrderLots(), MODE_BID);
                success = err_code == 0;

                Print("HIT Peer SELL Stoploss exit_price ", exit_price,
                        " safePrice ", safePrice,
                        " debugPriceIsClosePrice ", debugPriceIsClosePrice, //whether the safePrice is the current price or high/low                 
                        " vSync.peer_stoploss ", vSync.peer_stoploss,
                        " vSync.peer_spread_point ", vSync.peer_spread_point,
                        " clearance ", clearance,
                        " PrevIsHitPeerStoploss ", PrevIsHitPeerStoploss);
            }

        }

        if (attempted && success) {

            sendData(exitAtPeerStoplossPacket(vSync, ""));
            SendNotification(StringFormat("EXIT AT PEER STOPlOSS\nPeer Ticket #%d\nOwn Ticket #%d\nBal. %s", vSync.peer_ticket, vSync.own_ticket, AccountBalance() + " " + AccountCurrency()));
            SwingUtilities.invokeLater(() -> 
                lblAlert.setText("SUCCESSFUL EXIT AT PEER STOPOLOSS PRICE")
            );

        } else if (attempted && !success) {
            String error = ErrorDescription(GetLastError());
            sendData(exitAtPeerStoplossPacket(vSync, error));
        }

    }

    String exitAtPeerStoplossPacket(VirtualSync vSync, String error) {

        boolean success = "".equals(error);

        String packet = "exit_at_peer_stoploss_success=" + success + TAB
                + "own_ticket=" + LongToString((long) vSync.own_ticket) + TAB
                + "peer_ticket=" + LongToString((long) vSync.peer_ticket) + TAB
                + "error=" + error + TAB;

        return packet;

    }

    int closeSelectedPosition(double lots, int mode) {

        int MAX_ATTEMPT = 3;
        int error = 0;

        for (int try_count = 0; try_count < MAX_ATTEMPT; try_count++) {
            boolean success = OrderClose(OrderTicket(), lots, MarketInfo(OrderSymbol(), mode), 5);

            error = GetLastError();

            if (!success && error == ERR_REQUOTE) {
                Sleep(200);
                Print(StringFormat("REQUOTE ERROR: RETRY CLOSE [%d] - Order ticket #%d", try_count, OrderTicket()));
                ResetLastError();
                RefreshRates();
                continue;
            } else {
                break;
            }
        }

        return error;
    }

    void sendPacketClose(TradePacket trade) {

        String data = "";

        for (int i = OrdersTotal() - 1; i > -1; i--) {

            if (OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                if (trade.ticket == OrderTicket()) {
                    String error = "";
                    boolean success = true;
                    boolean pending = false;
                    boolean is_partial_close = trade.partial_closed_lot_fraction > 0 && trade.partial_closed_lot_fraction < 1;

                    double lots = OrderLots();

                    if (is_partial_close) {
                        lots = OrderLots() * trade.partial_closed_lot_fraction;
                    }

                    if (OrderType() == OP_BUY) //BUY is enters at ASK price but closes at BID price
                    {
                        int err_code = closeSelectedPosition(lots, MODE_BID);
                        success = err_code == 0;
                    } else if (OrderType() == OP_SELL)//SELL is enters at BID price but closes at ASK price 
                    {
                        int err_code = closeSelectedPosition(lots, MODE_ASK);
                        success = err_code == 0;
                    } else //pending orders
                    {
                        pending = true;
                        success = OrderDelete(OrderTicket());
                    }

                    if (success && !is_partial_close) {
                        addTicketOfSyncClose(trade.ticket);//mark this order ticket as one of those generated by close operation
                        if ("true".equals(trade.force)) {
                            SwingUtilities.invokeLater(() -> 
                               lblAlert.setText(trade.reason)                                    
                             );
                            Print(trade.reason);
                        }
                    } else if (success && is_partial_close) {
                        addTicketOfSyncClose(trade.ticket);//mark this order ticket as one of those generated by close operation                     
                    } else {
                        if ("true".equals(trade.force)) {
                            String warning = "WARNING!!! Secure attempt to forcibly close order #" + trade.ticket + " failed!";
                            SwingUtilities.invokeLater(() -> 
                                lblAlert.setText(warning)
                            );
                            Print(warning);
                        }

                        error = ErrorDescription(GetLastError());

                        if (!pending) {
                            Print("OrderClose error ", error);
                        } else {
                            Print("OrderDelete error ", error);
                        }
                    }

                    data += closeSuccessPacket(success, trade, error);

                }

            }
        }

        if (!"".equals(data)) {
            sendData(data);
        }

    }

    void restoreTarget() {

        if (LastAutoModifiedTarget == 0) {
            return;
        }

        int SymbolDigits = ensureSameSymboDigitsWithPeer();

        if (SymbolDigits == UNDEFINED) {
            return;
        }

        for (int i = OrdersTotal() - 1; i > -1; i--) {

            if (OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {

                if (NormalizeDouble(OrderTakeProfit(), SymbolDigits)
                        == NormalizeDouble(LastAutoModifiedTarget, SymbolDigits)) {
                    continue;
                }

                PrintFormat("Order #%d - DETECTED MANUAL TARGET MODIFICATION WHICH IS NOT ALLOWED", OrderTicket());

                if (OrderModify(OrderTicket(), OrderOpenPrice(), OrderStopLoss(), LastAutoModifiedTarget, 0)) {
                    PrintFormat("Order #%d - TARGET HAS BEEN RESET BACK TO %s", OrderTicket(), DoubleToString(LastAutoModifiedTarget, _Digits));

                } else {
                    PrintFormat("Order #%d - FAILED TO RESET TARGET BACK TO %s", OrderTicket(), DoubleToString(LastAutoModifiedTarget, _Digits));

                    String error = ErrorDescription(GetLastError());
                    Print(error);
                }
            }
        }
    }

    void sendPacketSyncModifyTarget(TradePacket trade) {

        String data = "";

        for (int i = OrdersTotal() - 1; i > -1; i--) {

            if (OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                if (trade.ticket == OrderTicket()) {
                    if (OrderModify(OrderTicket(), OrderOpenPrice(), OrderStopLoss(), trade.target, 0)) {
                        LastAutoModifiedTarget = trade.target;

                        data += modifyTargetSuccessPacket(true, trade, "");

                        addTicketOfSyncModify(trade.ticket);

                    } else {

                        String error = ErrorDescription(GetLastError());

                        data += modifyTargetSuccessPacket(false, trade, error);
                    }

                }

            }
        }

        if (!"".equals(data)) {
            sendData(data);
        }

    }

    boolean findTradeByPacket(TradePacket trade) {

        for (int i = OrdersTotal() - 1; i > -1; i--) {
            if (OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {

                if (OrderMagicNumber() == COPIED_TRADE_MAGIC_NUMBER
                        && trade.ticket == OrderTicket()//NEW 
                        ) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean findHistoryByPacket(TradePacket trade) {

        for (int i = OrdersHistoryTotal() - 1; i > -1; i--) {
            if (OrderSelect(i, SELECT_BY_POS, MODE_HISTORY)) {

                if (!isOrderType()) {//we only want order type and not credit or balance as displayed in AccountHistory of the Terminal
                    continue;
                }

                if (OrderMagicNumber() == COPIED_TRADE_MAGIC_NUMBER
                        && trade.ticket == OrderTicket()//NEW 
                        ) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
   @Deprecated
   
      CODING FOR PARTIAL CLOSE FEATURE IS TOO COMPLEX AND CHALLENGING BECAUSE  WHEN 
      PARTIALLY CLOSE A NEW TICKET IS CREATE OF THE PARTIAL OPEN ORDER THUS POSES
      A VERY COMPLEX APPROACH FOR ENSURING SYNCHRONIZATION 
    
   double partialClosedOrderFraction(ulong partial_closed_ticket, double partial_lot, long open_tickets[], double open_lots[]){
        
        int size = ArraySize(open_tickets);
        
        for(int i =0; i < size; i++){
        
            if(open_tickets[i] != partial_closed_ticket){                        
               return open_lots[i] / ( open_lots[i] + partial_lot);
            }                                
        }
      
      return 0;
   
   } 
     */
    void addTicketOfSyncOrderSend(long ticket) {
        addSetItem(ticket, ticketsOfSyncCopy);
    }

    void addTicketOfSyncClose(long ticket) {
        addSetItem(ticket, ticketsOfSyncClose);
    }

    void addTicketOfSyncModify(long ticket) {
        addSetItem(ticket, ticketsOfSyncModify);
    }

    void addSetItem(long item, long items[]) {

        boolean isAlreadyAdded = contains(item, items);

        if (isAlreadyAdded) {
            return;
        }

        int new_size = ArraySize(items) + 1;
        ArrayResize(items, new_size);
        items[new_size - 1] = item;
    }

    void addSetItem(double item, double items[]) {

        boolean isAlreadyAdded = contains(item, items);

        if (isAlreadyAdded) {
            return;
        }

        int new_size = ArraySize(items) + 1;
        ArrayResize(items, new_size);
        items[new_size - 1] = item;
    }

    void addItem(long item, long items[]) {
        int new_size = ArraySize(items) + 1;
        ArrayResize(items, new_size);
        items[new_size - 1] = item;
    }

    void addItem(double item, double items[]) {
        int new_size = ArraySize(items) + 1;
        ArrayResize(items, new_size);
        items[new_size - 1] = item;
    }

    boolean isTicketOfSyncCopy(long ticket) {
        return contains(ticket, ticketsOfSyncCopy);
    }

    boolean isTicketOfSyncClose(long ticket) {
        return contains(ticket, ticketsOfSyncClose);
    }

    boolean isTicketOfSyncModify(long ticket) {
        return contains(ticket, ticketsOfSyncModify);
    }

    boolean contains(long item, long items[]) {
        int size = ArraySize(items);

        for (int i = 0; i < size; i++) {
            if (items[i] == item) {
                return true;
            }
        }
        return false;
    }

    boolean contains(double item, double items[]) {
        int size = ArraySize(items);

        for (int i = 0; i < size; i++) {
            if (items[i] == item) {
                return true;
            }
        }
        return false;
    }

    void clearTicketsOfSyncCopy() {
        ArrayResize(ticketsOfSyncCopy, 0);
    }

    void clearTicketsOfSyncClose() {
        ArrayResize(ticketsOfSyncClose, 0);
    }

    void clearTicketsOfSyncModify() {
        ArrayResize(ticketsOfSyncModify, 0);
    }

    void arrayAppend(long ticket, long ticketsOfOrder[]) {
        int new_size = ArraySize(ticketsOfOrder) + 1;
        ArrayResize(ticketsOfOrder, new_size);
        ticketsOfOrder[new_size - 1] = ticket;
    }

    void clearTicketsOfPlacementOrder() {
        ArrayResize(ticketsOfPlacementOrder, 0);
    }

    void sendPacketTrade(TradePacket trade) {

        double lot_size = trade.lot_size;

        String trade_pos = trade.position;

        int order_type = toIntOrderType(trade_pos);

        //Print("-------------------------------");
        //Print("trade.ticket=",trade.ticket);
        //Print("trade.origin_ticket=",trade.origin_ticket);
        //Print("trade.symbol=",trade.symbol);
        //Print("trade.open_price=",trade.open_price);
        //Print("trade.close_time=",trade.close_time);
        //Print("trade.position=",trade.position);
        //Print("order_type=",order_type);
        //Print("trade.lot_size=",trade.lot_size);
        //Print("trade.stoploss=",trade.stoploss);
        //Print("trade.target=",trade.target);
        if (!IsConnected()) {
            sendData(syncSendOrderSuccessPacket(false, -1, trade, "No connection."));
            return;
        }

        String data = "";

        int count_try = 0;

        while (count_try < 3) {
            count_try++;

            if (order_type == OP_BUY) {
                double entry_price = MarketInfo(trade.symbol, MODE_ASK);

                long ticket = OrderSend(trade.symbol, order_type, lot_size, entry_price, 100, trade.stoploss, trade.target,
                        "", COPIED_TRADE_MAGIC_NUMBER, 0);

                if (ticket > 0) {

                    addTicketOfSyncOrderSend(ticket);//mark this order ticket as one of those generated by sync send order operation

                    data = syncSendOrderSuccessPacket(true, ticket, trade, "");
                    break;
                } else {
                    String error = ErrorDescription(GetLastError());

                    data = syncSendOrderSuccessPacket(false, -1, trade, error);

                    Print("TRY : ", count_try, "OrderSend error ", error);
                }

            } else if (order_type == OP_SELL) {

                double entry_price = MarketInfo(trade.symbol, MODE_BID);

                long ticket = OrderSend(trade.symbol, order_type, lot_size, entry_price, 100, trade.stoploss, trade.target,
                        "", COPIED_TRADE_MAGIC_NUMBER, 0);

                if (ticket > 0) {

                    addTicketOfSyncOrderSend(ticket);//mark this order ticket as one of those generated by copy operation

                    data = syncSendOrderSuccessPacket(true, ticket, trade, "");
                    break;
                } else {
                    String error = ErrorDescription(GetLastError());

                    data = syncSendOrderSuccessPacket(false, -1, trade, error);
                    Print("TRY : ", count_try, "OrderSend error ", error);
                }

            } else {
                Print("Unknown order type ", order_type);
                return;
            }

            Sleep(1000); //important!

            //Important - avoid duplicate trade when there is a connection error which is possible. I observed the bug.
            //So make sure truly the trade is not duplicated because of connection error moment after the trade is
            //already sent to the server
            if (findTradeByPacket(trade)) {
                Print("GOOD! Avoided duplicate trade! - ", trade.symbol);
                break;//leave to avoid duplicate trade
            }
        }

        if (!"".equals(data)) {
            sendData(data);
        }

    }

    String syncSendOrderSuccessPacket(boolean success, long ticket, TradePacket trade, String error) {
        if ("sync_copy".equals(trade.action)) {
            return copySuccessPacket(success, ticket, trade, error);
        } else if ("sync_place_order".equals(trade.action)) {
            return placeOrderSuccessPacket(success, ticket, trade, error);
        }

        return "";
    }

    String placeOrderSuccessPacket(boolean success, long ticket, TradePacket trade, String error) {
        if ("market is closed".equals(error)) {
            IsMarketClosed = true;
        }

        arrayAppend(ticket, ticketsOfPlacementOrder); //important! force the order information to be sent on next ticket. only reliable way of selecting the order

        String packet = "place_order_success=" + success + TAB
                + "ticket=" + LongToString((long) ticket) + TAB
                + "uuid=" + trade.uuid + TAB
                + "error=" + error + TAB;

        return packet;
    }

    String copySuccessPacket(boolean success, long ticket, TradePacket trade, String error) {
        if ("market is closed".equals(error)) {
            IsMarketClosed = true;
        }

        String packet = "copy_success=" + success + TAB
                + "ticket=" + LongToString((long) ticket) + TAB
                + "origin_ticket=" + LongToString((long) trade.ticket) + TAB
                + "copy_signal_time=" + trade.signal_time + TAB
                + "copy_execution_time=" + (long) TimeCurrent() + TAB
                + "error=" + error + TAB;

        return packet;
    }

    String maximizeLockInProfitSuccessPacket(boolean success, long ticket, String stoploss, String error) {

        if ("market is closed".equals(error)) {
            IsMarketClosed = true;
        }

        return "ticket=" + LongToString(ticket) + TAB
                + "stoploss=" + stoploss + TAB
                + "maximize_lock_in_profit_success=" + success + TAB
                + "error=" + error + TAB;
    }

    String lockInProfitSuccessPacket(boolean success, TradePacket trade, String error) {

        if ("market is closed".equals(error)) {
            IsMarketClosed = true;
        }

        return "ticket=" + LongToString((long) trade.ticket) + TAB
                + "origin_ticket=" + trade.origin_ticket + TAB
                + "stoploss=" + DoubleToString(trade.stoploss) + TAB
                + "lock_in_profit_success=" + success + TAB
                + "error=" + error + TAB;
    }

    String exitOnToleranceTargetSuccessPacket(boolean success, TradePacket trade, String error) {

        if ("market is closed".equals(error)) {
            IsMarketClosed = true;
        }

        return "ticket=" + LongToString((long) trade.ticket) + TAB
                + "origin_ticket=" + trade.origin_ticket + TAB
                + "floating_balance=" + DoubleToString(trade.floating_balance) + TAB
                + "account_balance=" + DoubleToString(trade.account_balance) + TAB
                + "exit_on_tolerance_target_success=" + success + TAB
                + "error=" + error + TAB;
    }

    String closeSuccessPacket(boolean success, TradePacket trade, String error) {

        if ("market is closed".equals(error)) {
            IsMarketClosed = true;
        }

        String packet = "";

        packet += "ticket=" + LongToString((long) trade.ticket) + TAB
                + "close_signal_time=" + trade.signal_time + TAB
                + "close_execution_time=" + (long) TimeCurrent() + TAB
                + "error=" + error + TAB;

        if (trade.ticket > 0) {//means it is selected
            long close_time = 0;
            double close_price = 0;

            if (OrderSelect((long) trade.ticket, SELECT_BY_TICKET)) {
                close_time = (long) OrderCloseTime();
                close_price = OrderClosePrice();
            }

            if (close_time == 0) {//just in case it is still saying zero then i disagree
                close_time = (long) TimeCurrent();
            }

            packet += "close_time=" + close_time + TAB
                    + "close_price=" + close_price + TAB;
        }

        if ("sync_close".equals(trade.action)) {
            packet += "origin_ticket=" + trade.origin_ticket + TAB
                    + "close_success=" + success + TAB;

        } else if ("own_close".equals(trade.action)) {
            packet += "origin_ticket=" + trade.origin_ticket + TAB
                    + "partial_close_success=" + success + TAB;

        } else if ("own_close".equals(trade.action)) {
            packet += "force=" + trade.force + TAB
                    + "reason=" + trade.reason + TAB
                    + "own_close_success=" + success + TAB;
        }

        return packet;
    }

    String modifyTakeProfitSuccessPacket(boolean success, TradePacket trade, String error) {
        if ("market is closed".equals(error)) {
            IsMarketClosed = true;
        }

        return "modify_take_profit_success=" + success + TAB
                + "ticket=" + LongToString((long) trade.ticket) + TAB
                + "target=" + DoubleToString(trade.target) + TAB
                + "error=" + error + TAB;
    }

    String modifyTargetSuccessPacket(boolean success, TradePacket trade, String error) {
        if ("market is closed".equals(error)) {
            IsMarketClosed = true;
        }

        return "modify_target_success=" + success + TAB
                + "ticket=" + LongToString((long) trade.ticket) + TAB
                + "origin_ticket=" + trade.origin_ticket + TAB
                + "target=" + DoubleToString(trade.target) + TAB
                + "modify_target_signal_time=" + trade.signal_time + TAB
                + "modify_target_execution_time=" + (long) TimeCurrent() + TAB
                + "error=" + error + TAB;
    }

    String checkEnoughMoneyCommandResponse(boolean success, TradePacket trade, String response) {
        return "command=" + trade.command + TAB
                + "command_id=" + trade.command_id + TAB
                + "command_response=" + response + TAB
                + "command_success=" + success + TAB;
    }

    String checkTradableCommandResponse(boolean success, TradePacket trade, String response) {
        return "command=" + trade.command + TAB
                + "command_id=" + trade.command_id + TAB
                + "command_response=" + response + TAB
                + "command_success=" + success + TAB;
    }

    String getStrOrderType() {
        if (OrderType() == OP_BUY) {
            return "BUY";
        } else if (OrderType() == OP_BUYLIMIT) {
            return "BUYLIMIT";
        } else if (OrderType() == OP_BUYSTOP) {
            return "BUYSTOP";
        } else if (OrderType() == OP_SELL) {
            return "SELL";
        } else if (OrderType() == OP_SELLLIMIT) {
            return "SELLLIMIT";
        } else if (OrderType() == OP_SELLSTOP) {
            return "SELLSTOP";
        }

        return "";
    }

    boolean isOrderType() {
        return OrderType() == OP_BUY
                || OrderType() == OP_SELL
                || OrderType() == OP_BUYSTOP
                || OrderType() == OP_SELLSTOP
                || OrderType() == OP_BUYLIMIT
                || OrderType() == OP_SELLLIMIT;
    }

    int toIntOrderType(String pos) {

        if ("BUY".equals(pos)) {
            return OP_BUY;
        } else if ("BUYLIMIT".equals(pos)) {
            return OP_BUYLIMIT;
        } else if ("BUYSTOP".equals(pos)) {
            return OP_BUYSTOP;
        } else if ("SELL".equals(pos)) {
            return OP_SELL;
        } else if ("SELLLIMIT".equals(pos)) {
            return OP_SELLLIMIT;
        } else if ("SELLSTOP".equals(pos)) {
            return OP_SELLSTOP;
        }

        return -1;
    }

    boolean isOrderTradeStatusChange(ChangeStats stats) {

        stats.TradeCountChanged = false;
        stats.TradeCountIncreased = false;
        stats.TradeModified = false;
        stats.TradeSwapChanged = false;

        int total = OrdersTotal();
        int buy_count = 0;
        int sell_count = 0;
        double cum_stoploss = 0;
        double cum_target = 0;
        double cum_swap = 0;

        for (int i = 0; i < total; i++) {

            if (OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {

                cum_stoploss += OrderStopLoss();
                cum_target += OrderTakeProfit();
                cum_swap += OrderSwap();

                if (OrderType() == OP_BUY) {
                    buy_count++;
                } else if (OrderType() == OP_SELL) {
                    sell_count++;
                }

            }

        }

        boolean is_changed = false;

        if (buy_count != BuyCount
                || sell_count != SellCount) {

            if (buy_count > BuyCount || sell_count > SellCount) {
                stats.TradeCountIncreased = true;
            }

            BuyCount = buy_count;
            SellCount = sell_count;

            stats.TradeCountChanged = true;
            is_changed = true;
        }

        int h_total = OrdersHistoryTotal();

        if (HistoryTotal != h_total) {
            stats.TradeCountChanged = true;
            is_changed = true;
        }

        if (cum_stoploss != CumStoploss
                || cum_target != CumTarget) {
            CumStoploss = cum_stoploss;
            CumTarget = cum_target;

            stats.TradeModified = true;
            is_changed = true;
        }

        if (cum_swap != CumSwap) {
            CumSwap = cum_swap;

            stats.TradeSwapChanged = true;
            is_changed = true;
        }

        return is_changed;
    }

    void sendPlaceOrderData() {

        long failed_tickets[] = {};

        for (int i = 0; i < ArraySize(ticketsOfPlacementOrder); i++) {
            long ticket = ticketsOfPlacementOrder[i];
            if (OrderSelect((long) ticket, SELECT_BY_TICKET)) {

                if (!isOrderType()) {//we only want order type and not credit or balance as displayed in AccountHistory of the Terminal
                    continue;
                }

                String data = generateTradeStreamPacket(0);
                sendData(data);
            } else {
                arrayAppend(ticket, failed_tickets);
            }

        }

        clearTicketsOfPlacementOrder();

        if (ArraySize(failed_tickets) > 0) {
            ArrayCopy(ticketsOfPlacementOrder, failed_tickets);
        }
    }

    void sendDataAttrForSyncStateID() {

        String data = "";
        String tickets = "";

        int total = OrdersTotal();

        int count = 0;
        String tck = "";
        for (int i = 0; i < total; i++) {

            if (OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                //At this point the order was not generated by copy operation                      
                count++;
                tck = count == 1 ? LongToString(OrderTicket()) : "," + OrderTicket();
                tickets += tck;

            }

        }

        data += "data_for_sync_state_pair_id=" + tickets + TAB;
        sendData(data);

    }

    void sendSyncOrdersData() {

        String data = "";

        int total = OrdersTotal();
        for (int i = 0; i < total; i++) {
            if (OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                //in this case we need those generated by sync operations   
                if (isTicketOfSyncCopy(OrderTicket())) {
                    //only those by sync operation
                    data += generateTradeStreamPacket(0);
                }

            }

        }

        if (!"".equals(data)) {
            sendData(data);
        }
    }

    void sendTradeData(String prepend_data) {

        boolean new_trade_entries = false;
        boolean close_trades = false;

        //CODING FOR PARTIAL CLOSE FEATURE IS TOO COMPLEX AND CHALLENGING BECAUSE  WHEN 
        //PARTIALLY CLOSED A NEW TICKET IS CREATED OF THE PARTIAL OPEN ORDER THUS POSES
        //A VERY COMPLEX APPROACH FOR ENSURING SYNCHRONIZATION
        //boolean partial_close = false; //@Deprecated - TOO HARD TO DO  - SOLUTION PRONE TO ERROR
        long open_tickets[];
        double open_lots[];

        String data = ensureWithTab(prepend_data);

        int total = OrdersTotal();
        for (int i = 0; i < total; i++) {

            if (OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {

                //we will make sure we don't send packets for orders generated by copy operation  - it is useless to do so
                if (isTicketOfSyncCopy(OrderTicket())) {
                    continue;//do no include this order since it is one of those generated by copy operation
                }

                //At this point the order was not generated by copy operation                 
                data += generateTradeStreamPacket(0);
                new_trade_entries = true;
            }

        }

        if (new_trade_entries) {
            data += "new_trade_entries=true" + TAB;

            clearTicketsOfSyncCopy();//just clear off since the job is done at this time         
        }

        String history_data = "";
        String partial_data = "";
        int h_total = OrdersHistoryTotal();

        if (h_total > HistoryTotal) {

            int diff_closed = h_total - HistoryTotal;

            for (int i = h_total - 1; i > HistoryTotal - 1; i--) {
                if (OrderSelect(i, SELECT_BY_POS, MODE_HISTORY)) {

                    if (!isOrderType()) {//we only want order type and not credit or balance as displayed in AccountHistory of the Terminal
                        continue;
                    }

                    //we will make sure we don't send packets for orders generated by close operation  - it is useless to do so
                    if (isTicketOfSyncClose(OrderTicket())) {
                        continue;//do no include this order since it is one of those generated by close operation
                    }

                    //At this point the order was not generated by close operation                            
                    history_data += generateTradeStreamPacket(0);
                    close_trades = true;
                    //Print("history_data", history_data);       
                }

            }

            HistoryTotal = h_total;

        }

        if (close_trades) {//append history to data to be sent
            data += history_data + "close_trades=true" + TAB;

            clearTicketsOfSyncClose();//just clear off since the job is done at this time
            clearTicketsOfSyncModify();
        }

        sendData(data);

    }

    /*@Deprecated
void sendTradeModifyData()
{
   
      boolean modify_trades = false;

      String data = "";

      int total = OrdersTotal();
      for(int i=0; i < total; i++)
         {
         
            if(OrderSelect(i, SELECT_BY_POS, MODE_TRADES))
            {
                 data += generateTradeStreamPacket();
                 modify_trades = true; 
            }
            
         }
    
      if(modify_trades){
         data += "modify_trades=true" + TAB;        
      }
     
     
     sendData(data);
         
}  
     */
    boolean AccountInfoUsedByPeer() {

        int total_orders = OrdersTotal();

        TotalCommission = 0;
        TotalLotSize = 0;
        TotalSwap = 0;
        Position = "";

        for (int i = 0; i < total_orders; i++) {

            if (!OrderSelect(i, SELECT_BY_POS, MODE_TRADES)) {
                return false; //just leave - no room for error
            }

            TotalLotSize += OrderLots();
            TotalCommission += OrderCommission();
            TotalSwap += OrderSwap();
            Position = OrderType() == OP_BUY ? "BUY" : OrderType() == OP_SELL ? "SELL" : "";

        }

        return true;
    }

    String generateTradeStreamPacket(double partial_close_fraction) {

        String copy_sender_ticket = "";
        if (OrderMagicNumber() == COPIED_TRADE_MAGIC_NUMBER) {
            //copy_sender_ticket = extractCopyTicket(OrderComment());//REMOVED - Instead the server will set it
        }

        String position = getStrOrderType();

        if ("".equals(position)) {//possibly credit or balance as displayed in the AccountHistory
            return "";
        }

        double symbol_point = getUsableSymbolPoint(OrderSymbol());

        String data = "ticket=" + OrderTicket() + TAB
                + "symbol=" + refactorSymbol(OrderSymbol(), false) + TAB
                + "raw_symbol=" + OrderSymbol() + TAB
                + "point=" + symbol_point + TAB
                + "digits=" + SymbolInfoInteger(OrderSymbol(), SYMBOL_DIGITS) + TAB
                + "position=" + position + TAB
                + "open_price=" + OrderOpenPrice() + TAB
                + "close_price=" + OrderClosePrice() + TAB
                + "open_time=" + (long) OrderOpenTime() + TAB
                + "close_time=" + (long) OrderCloseTime() + TAB
                + "lot_size=" + OrderLots() + TAB
                + "symbol_commission_per_lot=" + (OrderCommission() / OrderLots()) + TAB
                + "account_expected_hedge_profit=" + ExpectedHedgeProfit + TAB
                + "target=" + OrderTakeProfit() + TAB
                + "stoploss=" + OrderStopLoss() + TAB
                + "partial_close_fraction=" + partial_close_fraction + TAB;

        return data;
    }

    boolean isUpperCaseChar(short c) {
        return (c >= 65 && c <= 90);
    }

    boolean isAlphabet(short c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

    String refactorSymbol(String symbol, boolean retain_case) {

        if (!isRegularSymbol(symbol)) {
            return symbol;
        }

        int begin = -1;
        int end = -1;
        int count = 0;

        StringReplace(symbol, "/", ""); // remove '/' character if any

        int len = StringLen(symbol);

        for (int i = 0; i < len; i++) {

            short c = (short) StringGetChar(symbol, i);

            if (isAlphabet(c)) {
                count++;
                if (count == 1) {
                    begin = i;
                }

                if (count == 6) {
                    end = i;
                    break;
                }

            } else {
                if (count < 6) {
                    count = 0;
                }

            }

        }

        symbol = StringSubstr(symbol, begin, end + 1);

        int index = StringFind(symbol, "/", 0);
        if (index > -1) {
            return symbol;
        }

        symbol = StringSubstr(symbol, 0, 3) + "/" + StringSubstr(symbol, 3, 3);

        if (!retain_case) {
            //StringToUpper(symbol);
            symbol = symbol.toUpperCase();
        }

        return symbol;
    }

    boolean isSevenLetterPair(String pair) {

        int len = StringLen(pair);

        if (len != 7) {
            return false;
        }

        int slash_count = 0;
        int slash_pos = 0;
        for (int i = 0; i < len; i++) {
            slash_pos = i;
            short c = (short) StringGetChar(pair, i);

            if (!isAlphabet(c)) {
                if (c == '/' && slash_count == 0) {
                    if (slash_pos != 3) {
                        return false;
                    }

                    slash_count++;
                } else {
                    return false;
                }

            }
        }

        return true;
    }

    boolean isSixLetterPair(String pair) {

        int len = StringLen(pair);

        if (len != 6) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            short c = (short) StringGetChar(pair, i);

            if (!isAlphabet(c)) {
                return false;
            }
        }

        return true;
    }

    boolean isRegularSymbol(String symbol) {

        String split[] = {};
        StringSplit(symbol, '.', split);

        String prefix = "";
        String suffix = "";
        String pair = "";
        int split_size = ArraySize(split);

        if (split_size == 1) {
            pair = split[0];
        }

        if (split_size == 2) {

            String part_1 = split[0];
            String part_2 = split[1];

            if (isSixLetterPair(part_1)) {
                pair = part_1;
                prefix = part_2;
            }

            if (isSevenLetterPair(part_1)) {
                pair = part_1;
                prefix = part_2;
            }

            if (isSixLetterPair(part_2)) {
                if (!"".equals(pair)) {
                    return false; // meaning both parts cannot be pair
                }

                pair = part_2;
                suffix = part_1;

            }

            if (isSevenLetterPair(part_2)) {

                if (!"".equals(pair)) {
                    return false; // meaning both part cannot be pair
                }

                pair = part_2;
                suffix = part_1;
            }

        }

        if (split_size == 3) {
            prefix = split[0];
            pair = split[1];
            suffix = split[2];

        }

        if (split_size > 3) {
            return false;
        }

        if (!isSixLetterPair(pair) && !isSevenLetterPair(pair)) {
            return false;
        }

        return true;
    }

    String defactorSymbol(String symb) {

        //String symbol = "abc.USD/JPY.xyz"; //mab at work  - replace if Symbol() later
        if (!isRegularSymbol(symb)) {
            return symb;
        }

        String symbol = Symbol();

        int begin = -1;
        int end = -1;
        int count = 0;
        boolean has_slash = StringFind(symbol, "/", 0) > -1;
        int len = StringLen(symbol);

        for (int i = 0; i < len; i++) {

            short c = (short) StringGetChar(symbol, i);

            if (isAlphabet(c) || c == '/') {
                count++;
                if (count == 1) {
                    begin = i;
                }

                if ((!has_slash && count == 6) || (has_slash && count == 7)) {
                    end = i;
                    break;
                }

            } else {
                if ((!has_slash && count < 6) || (has_slash && count < 7)) {
                    count = 0;
                }

            }

        }

        String prefix = begin > 0 ? StringSubstr(symbol, 0, begin) : "";
        String suffix = StringSubstr(symbol, end + 1, len - end - 1);

        String s = refactorSymbol(symbol, true);

        if (isUpperCaseChar((short) StringGetChar(s, 0))) {
            //StringToUpper(symb);
            symb = symb.toUpperCase();
        } else {
            //StringToLower(symb);
            symb = symb.toLowerCase();
        }

        boolean symb_has_slash = StringFind(symb, "/", 0) > -1;

        symb = StringSubstr(symb, begin, end - begin + 1);

        if (has_slash && !symb_has_slash) {
            symb = StringSubstr(symb, 0, 3) + "/" + StringSubstr(symb, 3, 3);
        } else if (!has_slash && symb_has_slash) {
            StringReplace(symb, "/", "");
        }

        return prefix + symb + suffix;

    }

    boolean openConnection() {

        //--- wait for server
        if (!IsStopped()) {

            ExtConnection = stc.Connect(Host, Port) ? 1 : 0;

            boolean isDisconnection = !PrintConnectionWaiting && !isConnectionOpen;
            if (ExtConnection == 1) {

                PrintConnectionWaiting = true;
                isConnectionOpen = true;
                Print("Client: connection opened");
                SwingUtilities.invokeLater(() -> 
                    lblAlert.setText("Client: connection opened")
                );
                return true;
            }

            if (PrintConnectionWaiting) {
                PrintConnectionWaiting = false;
                Print("Client: waiting for server");
                SwingUtilities.invokeLater(() -> 
                    lblAlert.setText("Client: waiting for server")
                );
                if (isDisconnection) {
                    sendEADisconnectionNotification();
                }
            }

        } else {

            if (PrintEAIsStopped) {
                PrintEAIsStopped = false;
                String str_print_stop = "ATTENTION: The EA has stopped running...Please reload";
                Print(str_print_stop);
                SwingUtilities.invokeLater(() -> 
                    lblAlert.setText(str_print_stop)
                );
                SendNotification(str_print_stop);
            }
        }

        return false;
    }

    void reconnect(String error_reason, int errCount) {

        String reconnMsg = errCount == 1
                ? "Reconnecting... after last error : " + error_reason
                : "Reconnecting after " + errCount + " successive errors : " + error_reason;

        Print(reconnMsg);
        SwingUtilities.invokeLater(() -> 
            lblAlert.setText(reconnMsg)
        );

        channelIfNot();

    }

    String ensureWithTab(String data) {
        return ensureEndWith(data, TAB);
    }

    String ensureEndWithNewLine(String data) {
        return ensureEndWith(data, NEW_LINE);
    }

    String ensureEndWith(String data, String ch) {

        if ("".equals(data)) {
            return "";//no need for new line character
        }

        if (!StringSubstr(data, StringLen(data) - 1, 1).equals(ch)) {
            data += ch;
        }

        return data;
    }

    void validateConnection() {
        //We now have a far more efficient commication channel with the remote end so we 
        //will only be pinging very infrequently just to notify us if the connection is lost 
        //which is not very likely  though in this our current implementation using C++ DLL

        int timeElapseInMinutesSinceLastPing = (int) (TimeCurrent() - lastPingTime) / 60;

        if (timeElapseInMinutesSinceLastPing >= PING_INTERVAL_IN_MINUTES) {
            lastPingTime = TimeCurrent();
            sendData(PING_PACKET);
        }

    }

    void sendData(String data) {

        if (data.equals("")) {
            return;
        }

        boolean is_ping = data.equals(PING_PACKET);

        //data = StringTrimLeft(data); //remove tailing TAB  
        //data = StringTrimRight(data);//remove tailing TAB
        data = data.trim();//In java

        data += TAB + "is_market_closed=" + (IsMarketClosed == true ? "true" : "false");

        data = ensureEndWithNewLine(data);

        if (!is_ping) {
            //Print("sendData ",data);//TESTING!!!
        }

        int size_str = StringLen(data);

        int result = stc.Send(data);

        if (result == -1) {
            if (!stc.IsSocketConnected()) {

                if (!is_ping) {
                    Print("Client: failed to send data because connection is closed [", stc.GetSyncLastError(), "]");
                    SwingUtilities.invokeLater(() -> 
                        lblAlert.setText(lastSyncTradeErrorDesc())
                    );
                } else {
                    Print("Pinging detected connection closed.");
                    SwingUtilities.invokeLater(() -> 
                        lblAlert.setText("Pinging detected connection closed.")
                    );
                }

                //isConnectionOpen = false; //force the EA to reinitialize
                sendEADisconnectionNotification();

                return;
            } else {
                Print("Client: failed to send data [", stc.GetSyncLastError(), "]");
                Print("Client: Contact Administrator to revolve send operation failure.");
                SwingUtilities.invokeLater(() -> 
                    lblAlert.setText("Client: Contact Administrator to revolve send operation failure.")
                );
            }

        }

    }

    String lastSyncTradeErrorDesc() {

        char[] errStr = new char[255];
        stc.GetSyncLastErrorDesc(errStr, 255);
        return CharArrayToString(errStr);
    }

    String receiveData() {

        int last_error = GetLastError();
        if (last_error != 0) {
            Print("Client: Error occured [", ErrorDescription(last_error), "]");
            SwingUtilities.invokeLater(() -> 
                lblAlert.setText(ErrorDescription(last_error))
            );
            ResetLastError();
        }

        String data = "";

        int dataLen = stc.GetData();

        if (dataLen >= 0) {

            fialReadCount = 0;

            //char buffer [10]; //@Deprecated - since it is static array it can not be resize in mql5. instead use 'char buffer []'
            char buffer[] = {}; //Dynamic array which can be resize in mql5      

            ArrayResize(buffer, dataLen); //resize the buffer to length of data available      

            if (dataLen > 0) {
                stc.PacketReceived(buffer, dataLen);
                data = CharArrayToString(buffer);
            }

        } else {//error occured

            fialReadCount++;

            String str_last_error = lastSyncTradeErrorDesc();

            if (!stc.IsSocketConnected()) {
                reconnect(str_last_error, 1);
            } else if (stc.IsSocketConnected() && fialReadCount >= 3) {
                SwingUtilities.invokeLater(() -> 
                    lblAlert.setText(str_last_error)
                );
                Print("Client: read String failed [", str_last_error, "]");

                stc.CloseSocket();
                Sleep(300);
                reconnect(str_last_error, fialReadCount);
            }

        }

        return data;
    }

//+------------------------------------------------------------------+
}
