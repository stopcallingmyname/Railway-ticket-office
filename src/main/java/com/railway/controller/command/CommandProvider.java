package com.railway.controller.command;

import com.railway.controller.command.impl.general.DefaultCommand;
import com.railway.controller.command.impl.general.SignInCommand;
import com.railway.controller.command.impl.general.SignOutCommand;
import com.railway.controller.command.impl.general.SignUpCommand;
import com.railway.controller.command.impl.moveto.general.MoveToLoginPageCommand;
import com.railway.controller.command.impl.moveto.general.MoveToMainPageCommand;
import com.railway.controller.command.impl.moveto.general.MoveToSignupPageCommand;

import java.util.EnumMap;

/**
 * The class CommandProvider. It helps to find a command with specified name.
 */
public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    private CommandProvider() {
        addGeneralCommands();
        addUserCommands();
//        addAdminCommands();
    }

    /**
     * Gets instance.
     *
     * @return the instance of command provider
     */
    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    /**
     * Gets command.
     *
     * @param commandName the command name
     * @return the command with specified name
     */
    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }

    private void addGeneralCommands() {
        commands.put(CommandType.SIGN_IN, new SignInCommand());
        commands.put(CommandType.SIGN_UP, new SignUpCommand());
        commands.put(CommandType.DEFAULT, new DefaultCommand());
//        commands.put(CommandType.GET_ROUTE, new GetRouteCommand());
//        commands.put(CommandType.SEARCH_ROUTES, new SearchRootesCommand());
//        commands.put(CommandType.GET_ROUTE_LIST, new GetRouteListCommand());
        commands.put(CommandType.MOVE_TO_MAIN_PAGE, new MoveToMainPageCommand());
        commands.put(CommandType.MOVE_TO_LOGIN_PAGE, new MoveToLoginPageCommand());
        commands.put(CommandType.MOVE_TO_SIGNUP_PAGE, new MoveToSignupPageCommand());
//        commands.put(CommandType.EMAIL_VERIFICATION, new EmailVerificationCommand());
    }

    private void addAdminCommands() {
//        commands.put(CommandType.GET_USERS, new GetUsersCommand());
//        commands.put(CommandType.ADD_ROUTE, new AddRouteCommand());
//        commands.put(CommandType.EDIT_ROUTE, new EditRouteCommand());
//        commands.put(CommandType.DELETE_ROUTE, new DeleteRouteCommand());
//        commands.put(CommandType.CHANGE_USER_ROLE, new ChangeUserRoleCommand());
//        commands.put(CommandType.CHANGE_USER_IS_ACTIVE, new ChangeUserIsAcriveCommand());
//        commands.put(CommandType.MOVE_TO_ADD_ROUTE_PAGE, new MoveToAddRoutePageCommand());
//        commands.put(CommandType.MOVE_TO_EDIT_ROUTE_PAGE, new MoveToEditRoutePageCommand());
    }

    private void addUserCommands() {
        commands.put(CommandType.SIGN_OUT, new SignOutCommand());
    }
}
