package com.ravi.cal.RaviCalculator;

import static spark.Spark.*;

public class WebCalculator {
    public static void main(String[] args) {
        port(8080);
        ipAddress("0.0.0.0");  // Ensures Spark listens on all interfaces

        get("/", (req, res) -> {
            res.type("text/html");
            return """
                <!DOCTYPE html>
                <html lang='en'>
                <head>
                    <meta charset='UTF-8'>
                    <title>Java Web Calculator</title>
                    <style>
                        body {
                            background-color: #1e1e2f;
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            color: #fff;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                            margin: 0;
                        }
                        .container {
                            background: #2a2a40;
                            padding: 40px;
                            border-radius: 12px;
                            box-shadow: 0 0 20px rgba(0, 255, 255, 0.2);
                            width: 400px;
                        }
                        h1 {
                            text-align: center;
                            color: #00ffff;
                        }
                        input[type=number], button {
                            width: 100%;
                            padding: 10px;
                            margin: 10px 0;
                            border: none;
                            border-radius: 6px;
                            font-size: 16px;
                        }
                        input[type=number] {
                            background-color: #3a3a55;
                            color: #fff;
                        }
                        button {
                            background-color: #00ffff;
                            color: #000;
                            font-weight: bold;
                            cursor: pointer;
                        }
                        .result {
                            margin-top: 20px;
                            padding: 15px;
                            background-color: #1a1a2e;
                            border-left: 5px solid #00ffff;
                        }
                    </style>
                </head>
                <body>
                    <div class='container'>
                        <h1>Java Web Calculator</h1>
                        <form method='get' action='/calculate'>
                            <input type='number' name='a' placeholder='Enter first number' required />
                            <input type='number' name='b' placeholder='Enter second number' required />
                            <button type='submit'>Calculate</button>
                        </form>
                    </div>
                </body>
                </html>
            """;
        });

        get("/calculate", (req, res) -> {
            res.type("text/html");
            try {
                long a = Long.parseLong(req.queryParams("a"));
                long b = Long.parseLong(req.queryParams("b"));
                Calculator calc = new Calculator(a, b);
                return """
                    <!DOCTYPE html>
                    <html lang='en'>
                    <head>
                        <meta charset='UTF-8'>
                        <title>Calculation Result</title>
                        <style>
                            body { background-color: #1e1e2f; font-family: Arial, sans-serif; color: #fff; padding: 20px; }
                            .result { background: #2a2a40; padding: 30px; border-radius: 10px; max-width: 500px; margin: auto; }
                            h2 { color: #00ffff; }
                            a { color: #00ffff; display: inline-block; margin-top: 20px; }
                        </style>
                    </head>
                    <body>
                        <div class='result'>
                            <h2>Calculation Results</h2>
                            <p><strong>First Number:</strong> """ + a + """</p>
                            <p><strong>Second Number:</strong> """ + b + """</p>
                            <p><strong>Sum:</strong> """ + calc.addFucn() + """</p>
                            <p><strong>Difference:</strong> """ + calc.subFucn() + """</p>
                            <p><strong>Product:</strong> """ + calc.mulFucn() + """</p>
                            <a href='/'>← Go Back</a>
                        </div>
                    </body>
                    </html>
                """;
            } catch (Exception e) {
                res.status(400);
                return "<h1>Error: Invalid input</h1><a href='/'>← Try again</a>";
            }
        });
    }
}
