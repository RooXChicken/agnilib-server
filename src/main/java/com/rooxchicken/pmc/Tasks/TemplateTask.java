package com.rooxchicken.pmc.Tasks;

import com.rooxchicken.pmc.PMC;

public class TemplateTask extends Task
{

    public TemplateTask(PMC _plugin)
    {
        super(_plugin);

        tickThreshold = 4;
    }

    @Override
    public void run()
    {
        
    }
}
