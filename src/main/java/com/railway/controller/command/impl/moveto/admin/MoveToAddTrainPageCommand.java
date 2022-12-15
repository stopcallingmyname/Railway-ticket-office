package com.railway.controller.command.impl.moveto.admin;

import com.railway.controller.command.Command;
import com.railway.controller.command.PagePath;
import com.railway.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class MoveToAddTrainPageCommand.
 */
public class MoveToAddTrainPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToAddTrainPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ADD_TRAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}
