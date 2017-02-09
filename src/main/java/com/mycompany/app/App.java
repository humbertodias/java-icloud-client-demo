package com.mycompany.app;


import com.github.tmyroadctfig.icloud4j.DriveNode;
import com.github.tmyroadctfig.icloud4j.DriveService;
import com.github.tmyroadctfig.icloud4j.ICloudService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.varia.NullAppender;

import java.util.List;
import java.util.Map;

public class App {


    static {
        org.apache.log4j.BasicConfigurator.configure(new NullAppender());
    }

    public static void main(String... args) {

        if (args.length < 3) throw new RuntimeException("Missing client-id e-mail password!");

        String clientId = args[0];
        String username = args[1];
        String password = args[2];

        ICloudService iCloudService = new ICloudService(clientId);
        Map<String, Object> map = iCloudService.authenticate(username, password.toCharArray());
        map.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v));


        System.out.println("\n[List Files]");
        DriveService driveService = new DriveService(iCloudService);
        DriveNode root = driveService.getRoot();
        List<DriveNode> children = root.getChildren();

        for (DriveNode node : children) {

            System.out.printf("%s - %s\n",
                    StringUtils.rightPad(node.getNodeDetails().name, 20, " "),
                    StringUtils.rightPad(node.getNodeDetails().type, 20, " ")
            );

        }


    }

}