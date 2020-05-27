//
// @author LeoLiu
//

#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include "log/log.h"
#include "sql/sqlite3.h"

static int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    int i;
    for (i = 0; i < argc; i++) {
        LOGD("%s = %s", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    return 0;
}

extern "C"
JNIEXPORT jint
JNICALL
Java_com_lqh_database_DBManager_init(JNIEnv *env, jclass, jboolean debug) {
    LOGI("db version 202005050505");

    do {
        sqlite3 *db;
        char *zErrMsg = 0;
        int rc = sqlite3_open("/sdcard/a.db", &db);
        if (rc) {
            LOGI("Can't open database");
            break;
        }
        char *created_sql = "CREATE TABLE COMPANY("  \
         "ID INT PRIMARY KEY     NOT NULL," \
         "NAME           TEXT    NOT NULL," \
         "AGE            INT     NOT NULL," \
         "ADDRESS        CHAR(50)," \
         "SALARY         REAL );";
        rc = sqlite3_exec(db, created_sql, callback, 0, &zErrMsg);
        if (rc != SQLITE_OK) {
            LOGI("Table created error: %d\n", rc);
            sqlite3_close(db);
            break;
        }
        char *insert_sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "  \
         "VALUES (1, 'Paul', 32, 'California', 20000.00 ); " \
         "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "  \
         "VALUES (2, 'Allen', 25, 'Texas', 15000.00 ); "     \
         "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)" \
         "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );" \
         "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)" \
         "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
        rc = sqlite3_exec(db, insert_sql, callback, 0, &zErrMsg);
        if (rc != SQLITE_OK) {
            LOGI("Table insert error: %d\n", rc);
            sqlite3_close(db);
            break;
        }
        char *select_sql = "SELECT * from COMPANY";
        rc = sqlite3_exec(db, select_sql, callback, 0, &zErrMsg);
        if (rc != SQLITE_OK) {
            LOGI("Table select error: %d\n", rc);
            sqlite3_close(db);
            break;
        }
        char *delete_sql = "DELETE from COMPANY where ID=2; " \
         "SELECT * from COMPANY";
        rc = sqlite3_exec(db, delete_sql, callback, 0, &zErrMsg);
        if (rc != SQLITE_OK) {
            LOGI("Table delete: %d\n", rc);
            sqlite3_close(db);
            break;
        }
        sqlite3_close(db);
    } while (false);
    return 100;
}