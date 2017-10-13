<body>

 <!--Loading the AJAX API-->
                <script type="text/javascript" src="js/gclibrary/jsapi.js"></script>
                <script type="text/javascript" src="js/gclibrary/core.js"></script>
                <script type="text/javascript" src="js/gclibrary/core1.js"></script>
                    
            <div class="grid_16"  style="padding: 10px 0px 10px 0px;" id="whiteBox">
                <script type="text/javascript" src="js/jquery.js"></script>
                <script type="text/javascript">
                            google.setOnLoadCallback(drawChart);
                    <%--        var graphData = <% 
                                      out.print("[ "
                                                + "['Day', 'Sales'],");
                                      int i=0;
                                       while (i<= soldOn.size()-1){
                                            if (i< soldOn.size()-1){
                                               out.println("['Day "+soldOn.get(i) +"',  "+price.get(i) +" ],");
                                            }
                                            else {
                                               out.println("['"+soldOn.get(i) +"',  "+price.get(i) +" ]");
                                            }
                                            i++;
                                        }
                                        out.print("];");
                            %>;--%>
                                    
                            <%--      function drawChart() {
                              var data = google.visualization.arrayToDataTable
                              <%--([
                                ['Year', 'Sales', 'Expenses'],
                                ['1',  1000,      400],
                                ['2',  1170,      460],
                                ['3',  660,       1120],
                                ['4',  1030,      540],
                                ['5',  600,      650],
                                ['6',  780,      890],
                                ['7',  555,      457],
                                ['8',  456,      125],
                                ['9',  500,      756],
                                ['10',  1030,      630],
                                ['11',  660,      780],
                                ['12',  478,      445],
                                ['13',  50,      540],
                                ['14',  450,      235],
                                ['15',  1234,      667]
                              ]);--%>
                 
                      var options = {
                                title: 'Top 10 products sold',
                                  vAxis: {title: "Units Sold"},
                                  hAxis: {title: "Product Names"}
                              };

                           s
                            
                </script>
                <div id="chart_div_lineTop10Sold" style="width: 900px; height: 300px;"></div>
                
            </div> 

</body>