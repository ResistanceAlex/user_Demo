<template>
    <div>
        {{msg}}
        <br>
        <br>
        <!--搜索框和搜索按钮-->
        <el-row>
            <el-input
            placeholder="请输入内容"
            v-model="input"
            clearable>
            <template slot="append">
		        <el-button type="primary" plain @click="searchUserByName">查找</el-button>
	        </template>
            </el-input>
        </el-row>
        <br>
        <!--显示表格-->
        <el-table
            :data="users"
            border
            style="width: 100%">
            <el-table-column
            prop="id"
            label="编号"
            width="180">
            </el-table-column>
            <el-table-column
            prop="name"
            label="姓名"
            width="180">
            </el-table-column>
            <el-table-column
            prop="gender"
            label="性别"
            width="180">
            </el-table-column>
            <el-table-column
            prop="age"
            label="年龄">
            </el-table-column>
            <el-table-column
            prop="other"
            label="其他">
            </el-table-column>
            <el-table-column
            prop="op"
            label="操作">
            <template slot-scope="scope">
                <el-button
                size="mini"
                @click="centerDialogVisibleUpdate=true;handleEdit(scope.row)">编辑</el-button>
                <el-button
                size="mini"
                type="danger"
                slot="reference"
                @click="dialogVisibleDelete = true;handleDelete(scope.row.id)">删除</el-button>
            </template>
            </el-table-column>
        </el-table>
        <!--分页模块-->
        <div class="pagination">
            <el-pagination
                background
                layout="total, prev, pager, next"
                :current-page="query.pageNum"
                :page-size="query.pageSize"
                :total="pageTotal"
                @current-change="handleCurrentChange"
            ></el-pagination>
        </div>
        <!--添加用户-->
        <br>
        <el-button type="success" plain @click="centerDialogVisibleSave = true">添加用户</el-button>

        <!--删除用户弹窗-->
        <el-dialog
            title="提示"
            :visible.sync="dialogVisibleDelete"
            width="30%">
            <span>确认删除此用户吗？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisibleDelete = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisibleDelete = false;DeleteUser(deleteNum)">确 定</el-button>
            </span>
        </el-dialog>

        <!--添加用户弹窗-->
        <el-dialog
        title="创建新的用户信息"
        :visible.sync="centerDialogVisibleSave"
        width="30%"
        center>
            <span>保存界面</span>
            <el-form :model="insertFrom">
                <el-form-item label="用户姓名" :label-width="formLabelWidth">
                    <el-input v-model="insertFrom.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth">
                    <el-input v-model="insertFrom.gender" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="年龄" :label-width="formLabelWidth">
                    <el-input v-model="insertFrom.age" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="其他" :label-width="formLabelWidth">
                    <el-input v-model="insertFrom.other" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisibleSave = false">取 消</el-button>
                <el-button type="primary" @click="centerDialogVisibleSave = false;saveUser()">确 定</el-button>
            </span>
        </el-dialog>

        <!--编辑用户弹窗-->
        <el-dialog
        title="编辑用户信息"
        :visible.sync="centerDialogVisibleUpdate"
        width="30%"
        center>
        <span>编辑界面</span>
        <el-form :model="updateFrom">
            <el-form-item label="用户姓名" :label-width="formLabelWidth">
                <el-input v-model="updateFrom.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth">
                <el-input v-model="updateFrom.gender" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="年龄" :label-width="formLabelWidth">
                <el-input v-model="updateFrom.age" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="其他" :label-width="formLabelWidth">
                <el-input v-model="updateFrom.other" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisibleUpdate = false">取 消</el-button>
            <el-button type="primary" @click="centerDialogVisibleUpdate = false;updateUser()">确 定</el-button>
        </span>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name:"User",
        data(){
            return{
                centerDialogVisibleSave: false,
                centerDialogVisibleUpdate: false,
                dialogVisibleDelete: false,
                input:"",
                msg:"用户管理",
                users:[
                    {
                        "id":"",
                        "name":"",
                        "gender":"",
                        "age":"",
                        "other":""
                    }
                ],
                query:{
                    pageNum:1,
                    pageSize:5,
                },
                tableData: [],
                pageTotal: 0,
                insertFrom:{
                    "name":"",
                    "gender":"",
                    "age":"",
                    "other":""
                },
                updateFrom:{
                    "id":"",
                    "name":"",
                    "gender":"",
                    "age":"",
                    "other":""
                },
                visible:false,
                deleteNum:0,
                formLabelWidth: '120px'
            }
        },
        created(){
            this.getData(this.query.pageNum,this.query.pageSize)
        },
        methods:{
        getData(pageNum,pageSize){//获取一页的数据
            axios({
                method:'get',
                url:'http://localhost:8981/user/allUsers/'+pageNum+'/'+pageSize,
            })
            .then(resp =>{
                this.users = resp.data.list;
                this.pageTotal = resp.data.total
            })
        },
        handleCurrentChange(value){//获取当前页码
            this.query.pageNum = value;
            this.getData(this.query.pageNum,this.query.pageSize);
        },
        handleDelete(id){
            this.deleteNum = id
        },
        DeleteUser(id){
            axios({
                method:'get',
                url:'http://localhost:8981/user/delete/'+id,
            })
            .then(this.$router.go(0))
        },
        handleSearchById(id){
            axios({
                method:'get',
                url:'http://localhost:8981/user/findUserById/'+id,
            })
            .then(resp => {
                this.updateFrom = resp.data.list;
            })
        },
        handleEdit(row){
            this.updateFrom.id = row.id,
            this.updateFrom.name = row.name,
            this.updateFrom.gender = row.gender,
            this.updateFrom.age = row.age,
            this.updateFrom.other = row.other
        },
        updateUser(){
            axios({
                method:'post',
                url:'http://localhost:8981/user/update/',
                data:{
                    "id":this.updateFrom.id,
                    "name":this.updateFrom.name,
                    "age":this.updateFrom.age,
                    "gender":this.updateFrom.gender,
                    "other":this.updateFrom.other
                }
            })
            .then(this.$router.go(0))
        },
        searchUserByName(){
            axios({
                method:'get',
                url:'http://localhost:8981/user/findUsers/'+this.input+'/'+this.query.pageNum+'/'+this.query.pageSize,
            })
            .then(resp =>{
                this.users = resp.data.list;
                this.pageTotal = resp.data.total
            })
        },
        saveUser(){
            axios({
                method:'post',
                url:'http://localhost:8981/user/insert/',
                data:{
                    "name":this.insertFrom.name,
                    "age":this.insertFrom.age,
                    "gender":this.insertFrom.gender,
                    "other":this.insertFrom.other
                }
            })
            .then(this.$router.go(0))
        }
    }
}
</script>

<style scoped>

</style>